package pedidos.api.dto.produto.estoque;

import pedidos.api.model.ProdutoEstoque;

public record DadosDetalhamentoProdutoEstoque(
        Long id,
        Integer quantidade,
        Long idProduto
) {
    public DadosDetalhamentoProdutoEstoque(ProdutoEstoque produtoEstoque) {
        this(
                produtoEstoque.getId(), produtoEstoque.getQuantidade(), produtoEstoque.getProduto().getId()
        );
    }
}
