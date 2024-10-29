package pedidos.api.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pedidos.api.dto.pedido.DadosCadastroItem;
import pedidos.api.dto.pedido.DadosDetalhamentoItem;
import pedidos.api.dto.pedido.DadosDetalhamentoPedido;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.model.Item;
import pedidos.api.model.Pedido;
import pedidos.api.model.Produto;
import pedidos.api.repository.ItemRepository;
import pedidos.api.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService extends WeakEntityService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Produto retirarProdutoEmEstoque(Long idProduto, Integer quantidadeRetirada) {
        Produto produto = produtoRepository.getReferenceById(idProduto);
        if (quantidadeRetirada > produto.getQuantidadeEmEstoque()) {
            throw new ValidacaoException("Produto não está disponível em estoque!");
        }
        produto.retirarEmEstoque(quantidadeRetirada);
        return produto;
    }

    public List<DadosDetalhamentoItem> adicionarItensAoPedido(
            List<DadosCadastroItem> itens, Pedido pedido, PedidoService pedidoService) {
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = new ArrayList<>();
        for (DadosCadastroItem dadosCadastroItem: itens) {
            Produto produto = pedidoService.retirarProdutoEmEstoque(dadosCadastroItem.idProduto(),
                    dadosCadastroItem.quantidade());
            Item item = new Item(dadosCadastroItem,pedido,produto);
            itemRepository.save(item);
            DadosDetalhamentoItem dadosDetalhamentoItem = new DadosDetalhamentoItem(item);
            dadosDetalhamentoItemList.add(dadosDetalhamentoItem);
        }
        return dadosDetalhamentoItemList;
    }

    public List<DadosDetalhamentoPedido> capturarItensDeListaDePedidos(Page<Pedido> pedidoList) {
        List<DadosDetalhamentoPedido> dadosDetalhamentoPedidoList = new ArrayList<>();
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

    public List<DadosDetalhamentoItem> capturarItensDePedido(Pedido pedido) {
        return itemRepository.findAllByPedido(pedido).stream()
                .map(DadosDetalhamentoItem::new).toList();
    }

    public void excluirItensDePedido(Pedido pedido) {
        itemRepository.deleteAllByPedido(pedido);
    }
}
