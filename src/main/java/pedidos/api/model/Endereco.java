package pedidos.api.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedidos.api.dto.pedido.DadosCadastroEndereco;
import pedidos.api.dto.pedido.DadosCadastroEnderencoCompleto;

@Embeddable
@Getter
@NoArgsConstructor
public class Endereco {
    private String bairro;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(DadosCadastroEnderencoCompleto enderencoCompleto, DadosCadastroEndereco endereco) {
        this.bairro = enderencoCompleto.bairro();
        this.rua = enderencoCompleto.logradouro();
        this.numero = endereco.numero();
        this.cidade = enderencoCompleto.localidade();
        this.estado = enderencoCompleto.estado();
        this.cep = endereco.cep();
    }
}
