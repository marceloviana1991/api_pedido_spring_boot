package pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidos.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
