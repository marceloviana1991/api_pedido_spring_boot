package pedidos.api.dto.pedido;

import pedidos.api.model.Item;

public record DadosDetalhamentoItem(
        Long idProduto,
        Long idPedido,
        Integer quantidade
) {
    public DadosDetalhamentoItem(Item item) {
        this(
                item.getProduto().getId(),
                item.getPedido().getId(),
                item.getQuantidade()
        );
    }
}
