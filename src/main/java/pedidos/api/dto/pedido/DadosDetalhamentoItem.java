package pedidos.api.dto.pedido;

import pedidos.api.model.Item;

public record DadosDetalhamentoItem(
        Long idItem,
        Long idProduto,
        Long idPedido,
        Integer quantidade
) {
    public DadosDetalhamentoItem(Item item) {
        this(
                item.getId(),
                item.getProduto().getId(),
                item.getPedido().getId(),
                item.getQuantidade()
        );
    }
}
