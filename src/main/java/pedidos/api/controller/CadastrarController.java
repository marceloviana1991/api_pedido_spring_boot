package pedidos.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.DadosMensagemGenerica;
import pedidos.api.dto.usuario.DadosCadastroUsuario;
import pedidos.api.dto.usuario.DadosDetalhamentoUsuario;
import pedidos.api.model.Usuario;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.service.entity.UsuarioService;

@RestController
@RequestMapping("/cadastrar")
public class CadastrarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMensagemGenerica> cadastrar(
            @Valid @RequestBody DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario(dadosCadastroUsuario);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new DadosMensagemGenerica(usuarioService.enviarEmailDeVerificacao(usuario,
                uriComponentsBuilder)));
    }

    @GetMapping("/{uuid}")
    @Transactional
    public ResponseEntity<?> verificarCadastro(@PathVariable String uuid, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.ativarCadastroUsuario(uuid, usuarioRepository);
        if (usuario != null) {
            var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
        }
        return ResponseEntity.badRequest().body(new DadosMensagemGenerica("Tempo de ativação expirado."));
    }
}
