package pedidos.api.dto.produto.estoque;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroProdutoEstoque(
        @NotNull
        Long idProduto,
        @NotNull
        Integer quantidade
) {
}
