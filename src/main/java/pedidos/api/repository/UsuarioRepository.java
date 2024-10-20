package pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidos.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
