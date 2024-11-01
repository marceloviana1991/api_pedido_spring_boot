package pedidos.api.service.entity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pedidos.api.dto.pedido.DadosCadastroItem;
import pedidos.api.dto.pedido.DadosCadastroPedido;
import pedidos.api.dto.pedido.DadosDetalhamentoItem;
import pedidos.api.dto.pedido.DadosDetalhamentoPedido;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.model.*;
import pedidos.api.repository.ItemRepository;
import pedidos.api.repository.PedidoRepository;
import pedidos.api.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PedidoService extends WeakEntityService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Produto retirarProdutoEmEstoque(Long idProduto, Integer quantidadeRetirada) {
        Produto produto = produtoRepository.getReferenceById(idProduto);
        if (quantidadeRetirada > produto.getQuantidadeEmEstoque()) {
            throw new ValidacaoException("Produto não está disponível em estoque!");
        }
        produto.retirarEmEstoque(quantidadeRetirada);
        return produto;
    }

    public List<Item> adicionarItensAoPedido(List<DadosCadastroItem> itens, Pedido pedido) {
        List<Item> ItemList = new ArrayList<>();
        for (DadosCadastroItem dadosCadastroItem: itens) {
            Produto produto = retirarProdutoEmEstoque(dadosCadastroItem.idProduto(),
                    dadosCadastroItem.quantidade());
            Item item = new Item(dadosCadastroItem,pedido,produto);
            itemRepository.save(item);
            ItemList.add(item);
        }
        return ItemList;
    }

    public void excluirItensDePedido(Pedido pedido) {
        itemRepository.deleteAllByPedido(pedido);
    }

    public Pedido cadastrar(DadosCadastroPedido dadosCadastroPedido, HttpServletRequest request) {
        Usuario usuario = capturarUsuarioLogado(request);
        Pedido pedido = new Pedido(usuario);
        pedidoRepository.save(pedido);
        return pedido;
    }

    public Page<Pedido> listar(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public List<Item> listarItens(Pedido pedido) {
        return itemRepository.findAllByPedido(pedido);
    }

    public Pedido detalhar(Long id, HttpServletRequest request) {
        Usuario usuario = capturarUsuarioLogado(request);
        Pedido pedido = pedidoRepository.getReferenceById(id);
        if (!Objects.equals(usuario, pedido.getUsuario()) && usuario.getTipoUsuario() == TipoUsuario.USER) {
            throw new ValidacaoException("Esse pedido não pertence ao usuário logado!");
        }
        return pedido;
    }

    public void excluir(Long id) {
        Pedido pedido = pedidoRepository.getReferenceById(id);
        excluirItensDePedido(pedido);
        pedidoRepository.deleteById(id);
    }
}
