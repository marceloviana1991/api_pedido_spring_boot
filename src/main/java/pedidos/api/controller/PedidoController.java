package pedidos.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.DadosMensagemGenerica;
import pedidos.api.dto.pedido.DadosCadastroPedido;
import pedidos.api.dto.pedido.DadosDetalhamentoPedido;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.service.entity.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@SecurityRequirement(name = "bearer-key")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarPedido(
            @Valid @RequestBody DadosCadastroPedido dadosCadastroPedido , UriComponentsBuilder uriComponentsBuilder,
            HttpServletRequest request) {
        try {
            DadosDetalhamentoPedido dadosDetalhamentoPedido = pedidoService.cadastrar(dadosCadastroPedido, request);
            var uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(dadosDetalhamentoPedido.idPedido()).toUri();
            return ResponseEntity.created(uri).body(dadosDetalhamentoPedido);
        } catch (ValidacaoException validacaoException) {
            return ResponseEntity.badRequest().body(new DadosMensagemGenerica(validacaoException.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoPedido>> listarPedidos(Pageable pageable) {
        List<DadosDetalhamentoPedido> dadosDetalhamentoPedidoList = pedidoService.listar(pageable);
        return ResponseEntity.ok(dadosDetalhamentoPedidoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPedido> detalharPedido(@PathVariable Long id, HttpServletRequest request) {
        try {
            DadosDetalhamentoPedido dadosDetalhamentoPedido = pedidoService.detalhar(id, request);
            return ResponseEntity.ok(dadosDetalhamentoPedido);
        } catch (ValidacaoException validacaoException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirPedido(@PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
