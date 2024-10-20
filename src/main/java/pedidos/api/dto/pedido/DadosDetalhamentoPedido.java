package pedidos.api.dto.pedido;

import pedidos.api.model.Pedido;

import java.util.List;

public record DadosDetalhamentoPedido(
        Long idPedido,
        Long idUsuario,
        List<DadosDetalhamentoItem> itens
) {
    public DadosDetalhamentoPedido(Pedido pedido, List<DadosDetalhamentoItem> itens) {
        this(
                pedido.getId(),
                pedido.getUsuario().getId(),
                itens
        );
    }
}
