package pedidos.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import pedidos.api.model.SituacaoUsuario;
import pedidos.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
    Page<Usuario> findAllBySituacaoUsuarioIs(Pageable pageable, SituacaoUsuario situacaoUsuario);
}
