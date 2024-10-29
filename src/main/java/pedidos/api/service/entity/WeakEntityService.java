package pedidos.api.service.entity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidos.api.model.Usuario;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.service.security.TokenService;

@Service
public class WeakEntityService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario capturarUsuarioLogado(HttpServletRequest request) {
        var tokenJWT = tokenService.recuperarToken(request);
        var subject = tokenService.getSubject(tokenJWT);
        return (Usuario) usuarioRepository.findByLogin(subject);
    }
}
