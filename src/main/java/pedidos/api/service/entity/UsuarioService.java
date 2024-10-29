package pedidos.api.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.model.Usuario;
import pedidos.api.model.UsuarioVerificador;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.repository.UsuarioVerificadorRepository;
import pedidos.api.service.security.EmailService;

import java.time.Instant;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioVerificadorRepository usuarioVerificadorRepository;

    public String enviarEmailDeVerificacao(Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioVerificador verificador = new UsuarioVerificador(usuario);
        usuarioVerificadorRepository.save(verificador);
        var uri = uriComponentsBuilder.path("/cadastrar/{uuid}").buildAndExpand(verificador.getUuid()).toString();
        return emailService.enviarEmailTexto(usuario.getEmail(),
                "Novo usuário cadastradon na API Rest de sistema de gerenciamento de cadastro de pedidos",
                "Você está recebendo com um path para validação de cadastro "
                        + uri);
    }

    public Usuario ativarCadastroUsuario(String uuid, UsuarioRepository usuarioRepository) {
        UsuarioVerificador verificador = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid));
        if (verificador.getDataExpiracao().compareTo(Instant.now()) >= 0) {
            verificador.getUsuario().ativarCadastro();
            Usuario usuario = verificador.getUsuario();
            usuarioVerificadorRepository.delete(verificador);
            return usuario;
        }
        Usuario usuario = verificador.getUsuario();
        usuarioVerificadorRepository.delete(verificador);
        usuarioRepository.delete(usuario);
        return null;
    }
}
