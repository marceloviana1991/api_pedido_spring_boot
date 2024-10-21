package pedidos.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedidos.api.dto.usuario.DadosAtualizacaoUsuario;
import pedidos.api.dto.usuario.DadosCadastroUsuario;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario = TipoUsuario.USER;

    public Usuario(DadosCadastroUsuario dadosCadastroUsuario) {
        this.login = dadosCadastroUsuario.login();
        this.senha = dadosCadastroUsuario.senha();
    }

    public void atualizarDados(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        this.login = (dadosAtualizacaoUsuario.login() != null) ? dadosAtualizacaoUsuario.login() : this.login;
        this.senha = (dadosAtualizacaoUsuario.senha() != null) ? dadosAtualizacaoUsuario.senha() : this.senha;
    }
}
