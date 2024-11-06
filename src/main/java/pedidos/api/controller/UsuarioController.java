package pedidos.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pedidos.api.dto.usuario.DadosAtualizacaoUsuario;
import pedidos.api.dto.usuario.DadosDetalhamentoUsuario;
import pedidos.api.infra.exception.ValidacaoException;
import pedidos.api.service.entity.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoUsuario>> listarUsuarios(Pageable pageable) {
        List<DadosDetalhamentoUsuario> dadosDetalhamentoUsuarioList = usuarioService.listar(pageable);
        return ResponseEntity.ok(dadosDetalhamentoUsuarioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(@PathVariable Long id) {
        try {
            DadosDetalhamentoUsuario dadosDetalhamentoUsuario = usuarioService.detalhar(id);
            return ResponseEntity.ok(dadosDetalhamentoUsuario);
        } catch (ValidacaoException validacaoException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoUsuario> atualizarUsuario(@Valid @RequestBody DadosAtualizacaoUsuario
                                                                          dadosAtualizacaoUsuario) {
        DadosDetalhamentoUsuario dadosDetalhamentoUsuario = usuarioService.atualizar(dadosAtualizacaoUsuario);
        return ResponseEntity.ok(dadosDetalhamentoUsuario);
    }
}
