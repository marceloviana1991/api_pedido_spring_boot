package pedidos.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pedidos.api.dto.usuario.DadosAtualizacaoUsuario;
import pedidos.api.dto.usuario.DadosCadastroUsuario;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario = TipoUsuario.USER;
    @Enumerated(EnumType.STRING)
    private SituacaoUsuario situacaoUsuario = SituacaoUsuario.PENDENTE;

    public Usuario(DadosCadastroUsuario dadosCadastroUsuario) {
        this.login = dadosCadastroUsuario.login();
        this.senha = new BCryptPasswordEncoder().encode(dadosCadastroUsuario.senha());
        this.email = dadosCadastroUsuario.email();
    }

    public void atualizarDados(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        this.login = (dadosAtualizacaoUsuario.login() != null) ? dadosAtualizacaoUsuario.login() : this.login;
        this.senha = (dadosAtualizacaoUsuario.senha() != null) ?
                new BCryptPasswordEncoder().encode(dadosAtualizacaoUsuario.senha()) : this.senha;
        this.email = (dadosAtualizacaoUsuario.email() != null) ? dadosAtualizacaoUsuario.email() : this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.tipoUsuario == TipoUsuario.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }


    public void ativarCadastro() {
        this.situacaoUsuario = SituacaoUsuario.ATIVO;
    }
}
