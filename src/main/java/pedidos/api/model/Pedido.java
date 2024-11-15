package pedidos.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedidos.api.dto.pedido.DadosCadastroEndereco;
import pedidos.api.dto.pedido.DadosCadastroEnderencoCompleto;

import java.time.LocalDateTime;


@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    @Embedded
    private Endereco endereco;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Pedido(Usuario usuario, DadosCadastroEnderencoCompleto enderencoCompleto, DadosCadastroEndereco endereco) {
        this.usuario = usuario;
        this.data = LocalDateTime.now();
        this.endereco = new Endereco(enderencoCompleto, endereco);
    }
}
