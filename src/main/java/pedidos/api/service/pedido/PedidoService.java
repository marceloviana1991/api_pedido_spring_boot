package pedidos.api.service.pedido;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.model.Produto;
import pedidos.api.model.Usuario;
import pedidos.api.repository.ProdutoRepository;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.service.security.TokenService;

@Service
public class PedidoService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Usuario capturarUsuarioLogado(HttpServletRequest request) {
        var tokenJWT = tokenService.recuperarToken(request);
        var subject = tokenService.getSubject(tokenJWT);
        return (Usuario) usuarioRepository.findByLogin(subject);
    }

    public Produto retirarProdutoEmEstoque(Long idProduto, Integer quantidadeRetirada) {
        Produto produto = produtoRepository.getReferenceById(idProduto);
        if (quantidadeRetirada > produto.getQuantidadeEmEstoque()) {
            throw new ValidacaoException("Produto não está disponível em estoque!");
        }
        produto.retirarEmEstoque(quantidadeRetirada);
        return produto;
    }
}
