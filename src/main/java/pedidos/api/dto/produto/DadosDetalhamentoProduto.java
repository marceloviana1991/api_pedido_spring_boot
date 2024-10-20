package pedidos.api.dto.produto;

import pedidos.api.model.Produto;

public record DadosDetalhamentoProduto(
        Long id,
        String nome,
        Double valor
) {
    public DadosDetalhamentoProduto(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getValor()
        );
    }
}
