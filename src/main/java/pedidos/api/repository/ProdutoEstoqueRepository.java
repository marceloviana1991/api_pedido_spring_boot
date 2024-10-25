package pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidos.api.model.Produto;
import pedidos.api.model.ProdutoEstoque;

public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Long> {
    ProdutoEstoque getReferenceByProduto(Produto produto);
}
