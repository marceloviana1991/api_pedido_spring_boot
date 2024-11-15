package pedidos.api.dto.pedido;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCadastroEnderencoCompleto(
        String bairro,
        String localidade,
        String estado,
        String logradouro
) {
}
