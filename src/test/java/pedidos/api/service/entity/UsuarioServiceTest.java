package pedidos.api.service.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pedidos.api.dto.usuario.DadosCadastroUsuario;
import pedidos.api.model.SituacaoUsuario;
import pedidos.api.model.TipoUsuario;
import pedidos.api.model.Usuario;
import pedidos.api.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private Usuario usuario;

    @Test
    @DisplayName("Instancia e salva um usuário")
    void cadastrarCenario1() {

        this.usuario = usuarioService.cadastrar(new DadosCadastroUsuario(
                "usuarios", "senha", "usuario@email.com"));

        Assertions.assertEquals(usuario.getSituacaoUsuario(), SituacaoUsuario.PENDENTE);
        Assertions.assertEquals(usuario.getTipoUsuario(), TipoUsuario.USER);
    }

}