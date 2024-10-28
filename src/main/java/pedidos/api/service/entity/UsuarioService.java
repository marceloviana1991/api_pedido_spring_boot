package pedidos.api.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void enviarEmailDeVerificacao(Usuario usuario) {
        UsuarioVerificador verificador = new UsuarioVerificador(usuario);
        usuarioVerificadorRepository.save(verificador);
        String log = emailService.enviarEmailTexto(usuario.getEmail(),
                "Novo usuário cadastrado",
                "Você está recebendo um email de cadastro o número para validação é " + verificador.getUuid());
        System.out.println(log);
    }

    public Usuario ativarCadastroUsuario(String uuid, UsuarioRepository usuarioRepository) {
        UsuarioVerificador verificador = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid));
        if (verificador.getDataExpiracao().compareTo(Instant.now()) >= 0) {
            verificador.getUsuario().ativarCadastro();
            return verificador.getUsuario();
        }
        Usuario usuario = verificador.getUsuario();
        usuarioVerificadorRepository.delete(verificador);
        usuarioRepository.delete(usuario);
        return null;
    }
}
