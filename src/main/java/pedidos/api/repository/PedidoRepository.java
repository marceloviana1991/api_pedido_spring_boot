package pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidos.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
