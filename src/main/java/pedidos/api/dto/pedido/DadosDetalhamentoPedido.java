package pedidos.api.dto.pedido;

import pedidos.api.model.Pedido;

import java.util.List;

public record DadosDetalhamentoPedido(
        Long idPedido,
        Long idUsuario,
        String bairro,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep,
        List<DadosDetalhamentoItem> itens
) {
    public DadosDetalhamentoPedido(Pedido pedido, List<DadosDetalhamentoItem> itens) {
        this(
                pedido.getId(),
                pedido.getUsuario().getId(),
                pedido.getEndereco().getBairro(),
                pedido.getEndereco().getRua(),
                pedido.getEndereco().getNumero(),
                pedido.getEndereco().getCidade(),
                pedido.getEndereco().getEstado(),
                pedido.getEndereco().getCep(),
                itens
        );
    }
}
