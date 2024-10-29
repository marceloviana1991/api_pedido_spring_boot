package pedidos.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pedidos.api.dto.usuario.DadosAtualizacaoUsuario;
import pedidos.api.dto.usuario.DadosDetalhamentoUsuario;
import pedidos.api.model.Usuario;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.service.entity.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

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



}
