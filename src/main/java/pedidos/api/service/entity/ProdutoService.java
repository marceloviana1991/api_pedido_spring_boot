package pedidos.api.service.entity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pedidos.api.dto.produto.DadosAtualizacaoProduto;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.DadosDetalhamentoProduto;
import pedidos.api.dto.produto.estoque.DadosCadastroProdutoEstoque;
import pedidos.api.infra.storage.Disco;
import pedidos.api.model.Produto;
import pedidos.api.model.Usuario;
import pedidos.api.repository.ProdutoRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ProdutoService extends WeakEntityService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Disco disco;

    @Transactional
    public DadosDetalhamentoProduto cadastrar(DadosCadastroProduto dadosCadastroProduto, HttpServletRequest request) {
        Usuario usuario = capturarUsuarioLogado(request);
        Produto produto = new Produto(dadosCadastroProduto, usuario);
        produtoRepository.save(produto);
        return new DadosDetalhamentoProduto(produto);
    }

    @Transactional
    public DadosDetalhamentoProduto adicionarEmEstoque(DadosCadastroProdutoEstoque dadosCadastroProdutoEstoque) {
        Produto produto = produtoRepository.getReferenceById(dadosCadastroProdutoEstoque.idProduto());
        produto.adicionarEmEstoque(dadosCadastroProdutoEstoque.quantidade());
        return new DadosDetalhamentoProduto(produto);
    }

    public List<DadosDetalhamentoProduto> listar(Pageable pageable) {
        Page<Produto> produtoPage = produtoRepository.findAll(pageable);
        return produtoPage.stream().map(DadosDetalhamentoProduto::new).toList();
    }

    public DadosDetalhamentoProduto detalhar(Long id) {
        Produto produto = produtoRepository.getReferenceById(id);
        return new DadosDetalhamentoProduto(produto);
    }

    @Transactional
    public DadosDetalhamentoProduto atualizar(DadosAtualizacaoProduto dadosAtualizacaoProduto, HttpServletRequest request) {
        Usuario usuario = capturarUsuarioLogado(request);
        Produto produto = produtoRepository.getReferenceById(dadosAtualizacaoProduto.id());
        produto.atualizarDados(dadosAtualizacaoProduto, usuario);
        return new DadosDetalhamentoProduto(produto);
    }

    @Transactional
    public DadosDetalhamentoProduto adicionarFoto(MultipartFile foto, Long id) throws IOException {
        Produto produto = produtoRepository.getReferenceById(id);
        if (produto.getFoto() != null) {
            disco.excluirFoto(produto.getFoto());
        }
        String nomeDoArquivo = disco.salvarFoto(foto);
        produto.adicionarFoto(nomeDoArquivo);
        return new DadosDetalhamentoProduto(produto);
    }
}
