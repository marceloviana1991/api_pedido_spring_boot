package pedidos.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.DadosMensagemGenerica;
import pedidos.api.dto.usuario.DadosCadastroUsuario;
import pedidos.api.dto.usuario.DadosDetalhamentoUsuario;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.service.entity.UsuarioService;

@RestController
@RequestMapping("/cadastrar")
public class CadastrarController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuario(
            @Valid @RequestBody DadosCadastroUsuario dadosCadastroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        DadosDetalhamentoUsuario dadosDetalhamentoUsuario = usuarioService.cadastrar(dadosCadastroUsuario,
                uriComponentsBuilder);
        return ResponseEntity.ok(dadosDetalhamentoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> reenviarEmail(@PathVariable Long id,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        usuarioService.reenviarEmail(id, uriComponentsBuilder);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> verificarCadastroDeUsuario(@PathVariable String uuid) {
        try {
            DadosDetalhamentoUsuario dadosDetalhamentoUsuario = usuarioService.ativarCadastroUsuario(uuid);
            return ResponseEntity.ok(dadosDetalhamentoUsuario);
        } catch (ValidacaoException validacaoException) {
            usuarioService.excluirCadastroPendenteExpirado(uuid);
            return ResponseEntity.badRequest().body(new DadosMensagemGenerica(validacaoException.getMessage()));
        }
    }
}
