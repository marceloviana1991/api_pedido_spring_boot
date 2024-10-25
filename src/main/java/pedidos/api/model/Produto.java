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
    private Integer quantidadeEmEstoque;

    public Produto(DadosCadastroProduto dadosCadastroProduto) {
        this.nome = dadosCadastroProduto.nome();
        this.valor = dadosCadastroProduto.valor();
        this.quantidadeEmEstoque = 0;
    }

    public void atualizarDados(DadosAtualizacaoProduto dadosAtualizacaoProduto) {
        this.nome = (dadosAtualizacaoProduto.nome() != null) ? dadosAtualizacaoProduto.nome() : this.nome;
        this.valor = (dadosAtualizacaoProduto.valor() != null) ? dadosAtualizacaoProduto.valor() : this.valor;
    }

    public void adicionarEmEstoque(Integer quantidadeAdicionada) {
        this.quantidadeEmEstoque = this.quantidadeEmEstoque + quantidadeAdicionada;
    }

    public void retirarEmEstoque(Integer quantidadeRetirada) {
        this.quantidadeEmEstoque = this.quantidadeEmEstoque - quantidadeRetirada;
    }
}
