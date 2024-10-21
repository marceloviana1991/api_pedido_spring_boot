package pedidos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pedidos.api.dto.usuario.DadosCadastroUsuario;
import pedidos.api.dto.usuario.DadosDetalhamentoUsuario;
import pedidos.api.model.Usuario;
import pedidos.api.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public DadosDetalhamentoUsuario cadastrar(@RequestBody DadosCadastroUsuario dadosCadastroUsuario) {
        Usuario usuario = new Usuario(dadosCadastroUsuario);
        usuarioRepository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    @GetMapping
    public List<DadosDetalhamentoUsuario> listar() {
        return usuarioRepository.findAll().stream().map(DadosDetalhamentoUsuario::new).toList();
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoUsuario detalhar(@PathVariable Long id) {
        return new DadosDetalhamentoUsuario(usuarioRepository.getReferenceById(id));
    }
}
