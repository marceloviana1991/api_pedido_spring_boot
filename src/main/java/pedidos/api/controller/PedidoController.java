package pedidos.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pedidos.api.dto.pedido.DadosCadastroItem;
import pedidos.api.dto.pedido.DadosCadastroPedido;
import pedidos.api.dto.pedido.DadosDetalhamentoItem;
import pedidos.api.dto.pedido.DadosDetalhamentoPedido;
import pedidos.api.model.Item;
import pedidos.api.model.Pedido;
import pedidos.api.model.Produto;
import pedidos.api.model.Usuario;
import pedidos.api.repository.ItemRepository;
import pedidos.api.repository.PedidoRepository;
import pedidos.api.repository.ProdutoRepository;
import pedidos.api.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    @Transactional
    public DadosDetalhamentoPedido cadastrar(@RequestBody DadosCadastroPedido dadosCadastroPedido) {
        Usuario usuario = usuarioRepository.getReferenceById(dadosCadastroPedido.idUsuario());
        Pedido pedido = new Pedido(usuario);
        pedidoRepository.save(pedido);
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = new ArrayList<>();
        for (DadosCadastroItem dadosCadastroItem: dadosCadastroPedido.itens()) {
            Produto produto = produtoRepository.getReferenceById(dadosCadastroItem.idProduto());
            Item item = new Item(dadosCadastroItem,pedido,produto);
            itemRepository.save(item);
            DadosDetalhamentoItem dadosDetalhamentoItem = new DadosDetalhamentoItem(item);
            dadosDetalhamentoItemList.add(dadosDetalhamentoItem);
        }
        return new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList);
    }

    @GetMapping
    public List<DadosDetalhamentoPedido> listar() {
        List<DadosDetalhamentoPedido> dadosDetalhamentoPedidoList = new ArrayList<>();
        List<Pedido> pedidoList = pedidoRepository.findAll();
        for (Pedido pedido: pedidoList) {
            List<Item> itemList = itemRepository.findAllByPedido(pedido);
            List<DadosDetalhamentoItem> dadosDetalhamentoItemList = new ArrayList<>();
            for (Item item: itemList) {
                dadosDetalhamentoItemList.add(new DadosDetalhamentoItem(item));
            }
            dadosDetalhamentoPedidoList.add(new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList));
        }
        return dadosDetalhamentoPedidoList;
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoPedido detalhar(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.getReferenceById(id);
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = itemRepository.findAllByPedido(pedido).stream()
                .map(DadosDetalhamentoItem::new).toList();
        return new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.getReferenceById(id);
        itemRepository.deleteAllByPedido(pedido);
        pedidoRepository.deleteById(id);
    }
}
