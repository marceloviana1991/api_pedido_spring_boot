package pedidos.api.dto.usuario;

import pedidos.api.model.SituacaoUsuario;
import pedidos.api.model.TipoUsuario;
import pedidos.api.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String email,
        TipoUsuario tipoUsuario,
        SituacaoUsuario situacaoUsuario
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getSituacaoUsuario()
        );
    }
}
