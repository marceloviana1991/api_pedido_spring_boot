package pedidos.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pedidos.api.dto.usuario.DadosAutenticacaoUsuario;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.infra.security.DadosTokenJWT;
import pedidos.api.model.SituacaoUsuario;
import pedidos.api.model.Usuario;
import pedidos.api.service.security.TokenService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacaoUsuario dadosAutenticacaoUsuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacaoUsuario.login(),
                dadosAutenticacaoUsuario.senha());
        var authentication = manager.authenticate(authenticationToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        if (usuario.getSituacaoUsuario() == SituacaoUsuario.PENDENTE) {
            throw new ValidacaoException("Cadastro de usuário com pendência de ativação");
        }
        var tokenJWT = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
