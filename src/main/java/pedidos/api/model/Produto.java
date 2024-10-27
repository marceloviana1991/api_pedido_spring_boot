package pedidos.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedidos.api.dto.produto.DadosAtualizacaoProduto;
import pedidos.api.dto.produto.DadosCadastroProduto;

import java.time.LocalDateTime;

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
    private LocalDateTime dataDeCadastro;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Produto(DadosCadastroProduto dadosCadastroProduto, Usuario usuario) {
        this.nome = dadosCadastroProduto.nome();
        this.valor = dadosCadastroProduto.valor();
        this.dataDeCadastro = LocalDateTime.now();
        this.usuario = usuario;
        this.quantidadeEmEstoque = 0;
    }

    public void atualizarDados(DadosAtualizacaoProduto dadosAtualizacaoProduto, Usuario usuario) {
        this.nome = (dadosAtualizacaoProduto.nome() != null) ? dadosAtualizacaoProduto.nome() : this.nome;
        this.valor = (dadosAtualizacaoProduto.valor() != null) ? dadosAtualizacaoProduto.valor() : this.valor;
        this.quantidadeEmEstoque = (dadosAtualizacaoProduto.quantidadeEmEstoque() != null) ?
                dadosAtualizacaoProduto.quantidadeEmEstoque() : this.getQuantidadeEmEstoque();
        if (dadosAtualizacaoProduto.nome() != null || dadosAtualizacaoProduto.valor() != null) {
            this.dataDeCadastro = LocalDateTime.now();
            this.usuario = usuario;
        }
    }

    public void adicionarEmEstoque(Integer quantidadeAdicionada) {
        this.quantidadeEmEstoque = this.quantidadeEmEstoque + quantidadeAdicionada;
    }

    public void retirarEmEstoque(Integer quantidadeRetirada) {
        this.quantidadeEmEstoque = this.quantidadeEmEstoque - quantidadeRetirada;
    }
}
