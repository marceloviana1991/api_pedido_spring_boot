package pedidos.api.service.entity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pedidos.api.dto.pedido.*;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.model.*;
import pedidos.api.repository.ItemRepository;
import pedidos.api.repository.PedidoRepository;
import pedidos.api.repository.ProdutoRepository;
import pedidos.api.service.viacep.ConsumoViaCepApi;

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

    @Autowired
    private ConsumoViaCepApi consumoViaCepApi;

    public Produto retirarProdutoEmEstoque(Long idProduto, Integer quantidadeRetirada) {
        Produto produto = produtoRepository.getReferenceById(idProduto);
        if (quantidadeRetirada > produto.getQuantidadeEmEstoque()) {
            throw new ValidacaoException("Produto não está disponível em estoque!");
        }
        produto.retirarEmEstoque(quantidadeRetirada);
        return produto;
    }

    public List<Item> adicionarItensAoPedido(List<DadosCadastroItem> dadosCadastroItemList, Pedido pedido) {
        List<Item> itemList = new ArrayList<>();
        dadosCadastroItemList.forEach(dadosCadastroItem -> {
            Produto produto = retirarProdutoEmEstoque(dadosCadastroItem.idProduto(),
                    dadosCadastroItem.quantidade());
            Item item = new Item(dadosCadastroItem,pedido,produto);
            itemList.add(item);
            itemRepository.save(item);
        });
        return itemList;
    }

    public void excluirItensDePedido(Pedido pedido) {
        itemRepository.deleteAllByPedido(pedido);
    }

    @Transactional
    public DadosDetalhamentoPedido cadastrar(DadosCadastroPedido dadosCadastroPedido, HttpServletRequest request) {
        DadosCadastroEnderencoCompleto dadosCadastroEnderencoCompleto = consumoViaCepApi.obterDados(
                dadosCadastroPedido.endereco().cep());
        Usuario usuario = capturarUsuarioLogado(request);
        Pedido pedido = new Pedido(usuario, dadosCadastroEnderencoCompleto, dadosCadastroPedido.endereco());
        pedidoRepository.save(pedido);
        List<Item> itemList = adicionarItensAoPedido(dadosCadastroPedido.itens(), pedido);
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = itemList.stream().map(DadosDetalhamentoItem::new)
                .toList();
        return new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList);
    }

    public List<DadosDetalhamentoPedido> listar(Pageable pageable) {
        Page<Pedido> pedidoList = pedidoRepository.findAll(pageable);
        List<DadosDetalhamentoPedido> dadosDetalhamentoPedidoList = new ArrayList<>();
        pedidoList.forEach(pedido -> {
            List<Item> itemList = listarItens(pedido);
            List<DadosDetalhamentoItem> dadosDetalhamentoItemList = itemList.stream().map(DadosDetalhamentoItem::new)
                    .toList();
            dadosDetalhamentoPedidoList.add(new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList));
        });
        return dadosDetalhamentoPedidoList;
    }

    public List<Item> listarItens(Pedido pedido) {
        return itemRepository.findAllByPedido(pedido);
    }

    public DadosDetalhamentoPedido detalhar(Long id, HttpServletRequest request) {
        Usuario usuario = capturarUsuarioLogado(request);
        Pedido pedido = pedidoRepository.getReferenceById(id);
        if (!Objects.equals(usuario, pedido.getUsuario()) && usuario.getTipoUsuario() == TipoUsuario.USER) {
            throw new ValidacaoException("Esse pedido não pertence ao usuário logado!");
        }
        List<Item> itemList = listarItens(pedido);
        List<DadosDetalhamentoItem> dadosDetalhamentoItemList = itemList.stream().map(DadosDetalhamentoItem::new)
                .toList();
        return new DadosDetalhamentoPedido(pedido, dadosDetalhamentoItemList);
    }

    @Transactional
    public void excluir(Long id) {
        Pedido pedido = pedidoRepository.getReferenceById(id);
        excluirItensDePedido(pedido);
        pedidoRepository.deleteById(id);
    }
}
