package pedidos.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos_estoque")
@Entity(name = "ProdutoEstoque")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    private Integer quantidade;

    public ProdutoEstoque (Produto produto) {
        this.produto = produto;
        this.quantidade = 0;
    }

    public void adicionarEmEstoque(Integer quantidade) {
        this.quantidade = this.quantidade + quantidade;
    }

    public void retirarEmEstoque(Integer quantidade) {
        this.quantidade = this.quantidade - quantidade;
    }
}
