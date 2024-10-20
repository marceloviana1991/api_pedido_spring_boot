package pedidos.api.dto.usuario;

import pedidos.api.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String senha
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
