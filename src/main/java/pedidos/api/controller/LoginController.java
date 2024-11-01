package pedidos.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pedidos.api.dto.usuario.DadosAutenticacaoUsuario;
import pedidos.api.infra.security.DadosTokenJWT;
import pedidos.api.service.entity.UsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacaoUsuario dadosAutenticacaoUsuario) {
        var tokenJWT = usuarioService.efetuarLogin(dadosAutenticacaoUsuario);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
