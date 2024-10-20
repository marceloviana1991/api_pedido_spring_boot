package pedidos.api.dto.pedido;

import java.util.List;

public record DadosCadastroPedido(
        Long idUsuario,
        List<DadosCadastroItem> itens
) {
}
