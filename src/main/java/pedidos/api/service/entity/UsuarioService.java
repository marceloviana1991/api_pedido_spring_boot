package pedidos.api.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.DadosMensagemGenerica;
import pedidos.api.dto.usuario.DadosAtualizacaoUsuario;
import pedidos.api.dto.usuario.DadosAutenticacaoUsuario;
import pedidos.api.dto.usuario.DadosCadastroUsuario;
import pedidos.api.dto.usuario.DadosDetalhamentoUsuario;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.infra.security.DadosTokenJWT;
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

    public void enviarEmailDeVerificacao(Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioVerificador verificador = new UsuarioVerificador(usuario);
        usuarioVerificadorRepository.save(verificador);
        var uri = uriComponentsBuilder.path("/cadastrar/{uuid}").buildAndExpand(verificador.getUuid()).toString();
        emailService.enviarEmailTexto(usuario.getEmail(),
                "Novo usuário cadastradon na API Rest de sistema de gerenciamento de cadastro de pedidos",
                "Você está recebendo com um path para validação de cadastro "
                        + uri);
    }

    @Transactional
    public DadosDetalhamentoUsuario ativarCadastroUsuario(String uuid) {
        UsuarioVerificador verificador = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid));
        if (verificador.getDataExpiracao().compareTo(Instant.now()) < 0) {
            throw new ValidacaoException("Tempo de validação expirado.");
        }
        verificador.getUsuario().ativarCadastro();
        Usuario usuario = verificador.getUsuario();
        usuarioVerificadorRepository.delete(verificador);
        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public void excluirCadastroPendenteExpirado(String uuid) {
        UsuarioVerificador verificador = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid));
        Usuario usuario = verificador.getUsuario();
        usuarioVerificadorRepository.delete(verificador);
        usuarioRepository.delete(usuario);
    }

    @Scheduled(cron = "0 15 7 * * *")
    public void excluirTodosCadastrosPendentes() {
        List<UsuarioVerificador> verificadorList = usuarioVerificadorRepository.findAll();
        verificadorList.forEach(verificador -> {
            Usuario usuario = verificador.getUsuario();
            usuarioVerificadorRepository.delete(verificador);
            usuarioRepository.delete(usuario);
        });
    }

    @Transactional
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dadosCadastroUsuario,
                                           UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario(dadosCadastroUsuario);
        usuarioRepository.save(usuario);
        enviarEmailDeVerificacao(usuario, uriComponentsBuilder);
        return new DadosDetalhamentoUsuario(usuario);
    }

    public DadosTokenJWT efetuarLogin(DadosAutenticacaoUsuario dadosAutenticacaoUsuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacaoUsuario.login(),
                dadosAutenticacaoUsuario.senha());
        var authentication = manager.authenticate(authenticationToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        if (usuario.getSituacaoUsuario() == SituacaoUsuario.PENDENTE) {
            throw new ValidacaoException("Cadastro de usuário com pendência de ativação");
        }
        var tokenJWT = tokenService.gerarToken(usuario);
        return new DadosTokenJWT(tokenJWT);
    }

    @Transactional
    public DadosDetalhamentoUsuario atualizar(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(dadosAtualizacaoUsuario.id());
        usuario.atualizarDados(dadosAtualizacaoUsuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    public List<DadosDetalhamentoUsuario> listar(Pageable pageable) {
        Page<Usuario> usuarioList = usuarioRepository.findAll(pageable);
        return usuarioList.stream().map(DadosDetalhamentoUsuario::new).toList();
    }

    public DadosDetalhamentoUsuario detalhar(Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return new DadosDetalhamentoUsuario(usuario);
    }
}
