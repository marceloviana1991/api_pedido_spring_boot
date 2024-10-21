package pedidos.api.dto.usuario;

import pedidos.api.model.TipoUsuario;
import pedidos.api.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        TipoUsuario tipoUsuario
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getTipoUsuario()
        );
    }
}
