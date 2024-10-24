package pedidos.api.dto.pedido;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroPedido(
        @NotNull
        @NotEmpty
        List<DadosCadastroItem> itens
) {
}
