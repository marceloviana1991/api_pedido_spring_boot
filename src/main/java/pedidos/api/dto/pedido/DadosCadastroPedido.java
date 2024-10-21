package pedidos.api.dto.pedido;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroPedido(
        @NotNull
        List<DadosCadastroItem> itens
) {
}
