package pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidos.api.model.Item;
import pedidos.api.model.Pedido;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByPedido(Pedido pedido);

    void deleteAllByPedido(Pedido pedido);
}
