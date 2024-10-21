package pedidos.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedidos.api.dto.produto.DadosAtualizacaoProduto;
import pedidos.api.dto.produto.DadosCadastroProduto;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;

    public Produto(DadosCadastroProduto dadosCadastroProduto) {
        this.nome = dadosCadastroProduto.nome();
        this.valor = dadosCadastroProduto.valor();
    }

    public void atualizarDados(DadosAtualizacaoProduto dadosAtualizacaoProduto) {
        this.nome = (dadosAtualizacaoProduto.nome() != null) ? dadosAtualizacaoProduto.nome() : this.nome;
        this.valor = (dadosAtualizacaoProduto.valor() != null) ? dadosAtualizacaoProduto.valor() : this.valor;
    }
}
