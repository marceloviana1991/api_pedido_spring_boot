package pedidos.api.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.usuario.DadosAtualizacaoUsuario;
import pedidos.api.dto.usuario.DadosAutenticacaoUsuario;
import pedidos.api.dto.usuario.DadosCadastroUsuario;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.model.SituacaoUsuario;
import pedidos.api.model.Usuario;
import pedidos.api.model.UsuarioVerificador;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.repository.UsuarioVerificadorRepository;
import pedidos.api.service.security.EmailService;
import pedidos.api.service.security.TokenService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioVerificadorRepository usuarioVerificadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    public String enviarEmailDeVerificacao(Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioVerificador verificador = new UsuarioVerificador(usuario);
        usuarioVerificadorRepository.save(verificador);
        var uri = uriComponentsBuilder.path("/cadastrar/{uuid}").buildAndExpand(verificador.getUuid()).toString();
        return emailService.enviarEmailTexto(usuario.getEmail(),
                "Novo usuário cadastradon na API Rest de sistema de gerenciamento de cadastro de pedidos",
                "Você está recebendo com um path para validação de cadastro "
                        + uri);
    }

    public Usuario ativarCadastroUsuario(String uuid) {
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

    public Usuario cadastrar(DadosCadastroUsuario dadosCadastroUsuario) {
        Usuario usuario = new Usuario(dadosCadastroUsuario);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public String efetuarLogin(DadosAutenticacaoUsuario dadosAutenticacaoUsuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacaoUsuario.login(),
                dadosAutenticacaoUsuario.senha());
        var authentication = manager.authenticate(authenticationToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        if (usuario.getSituacaoUsuario() == SituacaoUsuario.PENDENTE) {
            throw new ValidacaoException("Cadastro de usuário com pendência de ativação");
        }
        return tokenService.gerarToken(usuario);
    }

    public Usuario atualizar(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(dadosAtualizacaoUsuario.id());
        usuario.atualizarDados(dadosAtualizacaoUsuario);
        return usuario;
    }

    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario detalhar(Long id) {
        return usuarioRepository.getReferenceById(id);
    }
}
