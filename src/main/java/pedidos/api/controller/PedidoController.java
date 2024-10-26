package pedidos.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.pedido.DadosCadastroPedido;
import pedidos.api.dto.pedido.DadosDetalhamentoItem;
import pedidos.api.dto.pedido.DadosDetalhamentoPedido;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.service.entity.PedidoService;
import pedidos.api.model.*;
import pedidos.api.repository.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPedido> cadastrar(
            @Valid @RequestBody DadosCadastroPedido dadosCadastroPedido , UriComponentsBuilder uriComponentsBuilder,
            HttpServletRequest request) {
        Usuario usuario = pedidoService.capturarUsuarioLogado(request);
        Pedido pedido = new Pedido(usuario);
        pedidoRepository.save(pedido);
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = pedidoService.adicionarItensAoPedido(
                dadosCadastroPedido.itens(),pedido, pedidoService);
        var uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoPedido>> listar(Pageable pageable) {
        Page<Pedido> pedidoList = pedidoRepository.findAll(pageable);
        List<DadosDetalhamentoPedido> dadosDetalhamentoPedidoList = pedidoService.capturarItensDeListaDePedidos(
                pedidoList);
        return ResponseEntity.ok(dadosDetalhamentoPedidoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPedido> detalhar(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuario = pedidoService.capturarUsuarioLogado(request);
        Pedido pedido = pedidoRepository.getReferenceById(id);
        if (!Objects.equals(usuario, pedido.getUsuario()) && usuario.getTipoUsuario() == TipoUsuario.USER) {
            throw new ValidacaoException("Esse pedido não pertence ao usuário logado!");
        }
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = pedidoService.capturarItensDePedido(pedido);
        return ResponseEntity.ok(new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.getReferenceById(id);
        pedidoService.excluirItensDePedido(pedido);
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
