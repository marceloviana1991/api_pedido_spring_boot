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
        Pedido pedido = pedidoService.cadastrar(dadosCadastroPedido, request);
        List<Item> itemList = pedidoService.adicionarItensAoPedido(dadosCadastroPedido.itens(), pedido);
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = itemList.stream().map(DadosDetalhamentoItem::new)
                .toList();
        var uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoPedido>> listar(Pageable pageable) {
        Page<Pedido> pedidoList = pedidoService.listar(pageable);
        List<DadosDetalhamentoPedido> dadosDetalhamentoPedidoList = new ArrayList<>();
        pedidoList.forEach(pedido -> {
            List<Item> itemList = pedidoService.listarItens(pedido);
            List<DadosDetalhamentoItem> dadosDetalhamentoItemList = itemList.stream().map(DadosDetalhamentoItem::new)
                    .toList();
            dadosDetalhamentoPedidoList.add(new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList));
        });
        return ResponseEntity.ok(dadosDetalhamentoPedidoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPedido> detalhar(@PathVariable Long id, HttpServletRequest request) {
        Pedido pedido = pedidoService.detalhar(id, request);
        List<Item> itemList = pedidoService.listarItens(pedido);
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = itemList.stream().map(DadosDetalhamentoItem::new)
                .toList();
        return ResponseEntity.ok(new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
