package pedidos.api.service.entity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pedidos.api.dto.produto.DadosAtualizacaoProduto;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.estoque.DadosCadastroProdutoEstoque;
import pedidos.api.model.Produto;
import pedidos.api.model.Usuario;
import pedidos.api.repository.ProdutoRepository;

@Service
public class ProdutoService extends WeakEntityService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrar(DadosCadastroProduto dadosCadastroProduto, HttpServletRequest request) {
        Usuario usuario = capturarUsuarioLogado(request);
        Produto produto = new Produto(dadosCadastroProduto, usuario);
        produtoRepository.save(produto);
        return produto;
    }

    public Produto adicionarEmEstoque(DadosCadastroProdutoEstoque dadosCadastroProdutoEstoque) {
        Produto produto = produtoRepository.getReferenceById(dadosCadastroProdutoEstoque.idProduto());
        produto.adicionarEmEstoque(dadosCadastroProdutoEstoque.quantidade());
        return produto;
    }

    public Page<Produto> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto detalhar(Long id) {
        return produtoRepository.getReferenceById(id);
    }

    public Produto atualizar(DadosAtualizacaoProduto dadosAtualizacaoProduto, HttpServletRequest request) {
        Usuario usuario = capturarUsuarioLogado(request);
        Produto produto = produtoRepository.getReferenceById(dadosAtualizacaoProduto.id());
        produto.atualizarDados(dadosAtualizacaoProduto, usuario);
        return produto;
    }
}
