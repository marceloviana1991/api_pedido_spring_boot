package pedidos.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.usuario.DadosAtualizacaoUsuario;
import pedidos.api.dto.usuario.DadosAutenticacaoUsuario;
import pedidos.api.dto.usuario.DadosCadastroUsuario;
import pedidos.api.dto.usuario.DadosDetalhamentoUsuario;
import pedidos.api.infra.security.DadosTokenJWT;
import pedidos.api.infra.security.TokenService;
import pedidos.api.model.Usuario;
import pedidos.api.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@Valid @RequestBody DadosCadastroUsuario dadosCadastroUsuario,
                                              UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario(dadosCadastroUsuario);
        usuarioRepository.save(usuario);
        var uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoUsuario>> listar(Pageable pageable) {
        return ResponseEntity.ok(usuarioRepository.findAll(pageable).stream().map(DadosDetalhamentoUsuario::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuarioRepository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@Valid @RequestBody DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(dadosAtualizacaoUsuario.id());
        usuario.atualizarDados(dadosAtualizacaoUsuario);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacaoUsuario dadosAutenticacaoUsuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacaoUsuario.login(),
                dadosAutenticacaoUsuario.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
