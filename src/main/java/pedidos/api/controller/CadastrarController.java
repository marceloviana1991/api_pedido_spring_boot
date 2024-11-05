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
import pedidos.api.service.entity.UsuarioService;

@RestController
@RequestMapping("/cadastrar")
public class CadastrarController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMensagemGenerica> cadastrarUsuario(
            @Valid @RequestBody DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        DadosMensagemGenerica mensagemGenerica = usuarioService.cadastrar(dadosCadastroUsuario, uriComponentsBuilder);
        return ResponseEntity.ok(mensagemGenerica);
    }

    @GetMapping("/{uuid}")
    @Transactional
    public ResponseEntity<?> verificarCadastroDeUsuario(@PathVariable String uuid, UriComponentsBuilder uriComponentsBuilder) {
        Object verificacao = usuarioService.ativarCadastroUsuario(uuid);
        if (verificacao.getClass().isInstance(DadosDetalhamentoUsuario.class)) {
            DadosDetalhamentoUsuario dadosDetalhamentoUsuario = (DadosDetalhamentoUsuario) verificacao;
            var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(dadosDetalhamentoUsuario.id()).toUri();
            return ResponseEntity.created(uri).body(dadosDetalhamentoUsuario.id());
        }
        return ResponseEntity.badRequest().body(verificacao);
    }
}
