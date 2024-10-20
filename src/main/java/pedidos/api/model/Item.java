package pedidos.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedidos.api.dto.pedido.DadosCadastroItem;

@Table(name = "itens")
@Entity(name = "Item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    public Item(DadosCadastroItem dadosCadastroItem, Pedido pedido, Produto produto) {
        this.quantidade = dadosCadastroItem.quantidade();
        this.pedido = pedido;
        this.produto = produto;
    }
}
