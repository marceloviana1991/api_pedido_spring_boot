package pedidos.api.dto.pedido;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroItem(
        @NotNull
        Long idProduto,
        @NotNull
        Integer quantidade
) {
}
