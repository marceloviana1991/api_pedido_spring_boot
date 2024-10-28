package pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidos.api.model.Usuario;
import pedidos.api.model.UsuarioVerificador;

import java.util.UUID;

public interface UsuarioVerificadorRepository extends JpaRepository<UsuarioVerificador, Long> {
    UsuarioVerificador findByUuid(UUID uuid);
}
