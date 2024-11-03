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
import pedidos.api.service.entity.PedidoService;
import pedidos.api.model.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPedido> cadastrar(
            @Valid @RequestBody DadosCadastroPedido dadosCadastroPedido , UriComponentsBuilder uriComponentsBuilder,
            HttpServletRequest request) {
        DadosDetalhamentoPedido dadosDetalhamentoPedido = pedidoService.cadastrar(dadosCadastroPedido, request);
        var uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(dadosDetalhamentoPedido.idPedido()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoPedido);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoPedido>> listar(Pageable pageable) {
        List<DadosDetalhamentoPedido> dadosDetalhamentoPedidoList = pedidoService.listar(pageable);
        return ResponseEntity.ok(dadosDetalhamentoPedidoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPedido> detalhar(@PathVariable Long id, HttpServletRequest request) {
        DadosDetalhamentoPedido dadosDetalhamentoPedido = pedidoService.detalhar(id, request);
        return ResponseEntity.ok(dadosDetalhamentoPedido);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
