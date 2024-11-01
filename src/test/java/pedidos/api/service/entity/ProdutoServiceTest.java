package pedidos.api.service.entity;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.estoque.DadosCadastroProdutoEstoque;
import pedidos.api.model.Produto;
import pedidos.api.model.Usuario;
import pedidos.api.repository.ProdutoRepository;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.service.security.TokenService;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Produto produto;

    @Mock
    private Usuario usuario;

    @Mock
    private TokenService tokenService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Instancia e salva um produto")
    void cadastrarCenario1() {
        this.produto = produtoService.cadastrar(new DadosCadastroProduto("produto", 0.5), request);
        Assertions.assertEquals(produto.getQuantidadeEmEstoque(), 0);
    }

    @Test
    @DisplayName("Adiciona em estoque")
    void cadastrarCenario2() {
        produto = produtoService.cadastrar(new DadosCadastroProduto("produto", 0.5), request);
        DadosCadastroProdutoEstoque dadosCadastroProdutoEstoque = new DadosCadastroProdutoEstoque(1L, 5);
        given(produtoRepository.getReferenceById(dadosCadastroProdutoEstoque.idProduto())).willReturn(produto);
        produtoService.adicionarEmEstoque(dadosCadastroProdutoEstoque);
        Assertions.assertEquals( 5, produto.getQuantidadeEmEstoque());
    }
}