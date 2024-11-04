package pedidos.api.dto.produto;

import pedidos.api.model.Produto;

import java.time.LocalDateTime;

public record DadosDetalhamentoProduto(
        Long id,
        String nome,
        Double valor,
        Integer quantidadeEmEstoque,
        LocalDateTime dataDeCadastro,
        String foto
) {
    public DadosDetalhamentoProduto(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getQuantidadeEmEstoque(),
                produto.getDataDeCadastro(),
                produto.getFoto()
        );
    }
}
