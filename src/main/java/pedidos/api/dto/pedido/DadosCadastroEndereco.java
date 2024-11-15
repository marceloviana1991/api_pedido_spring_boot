package pedidos.api.dto.pedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
        String numero,
        @NotBlank @Pattern(regexp = "\\d{8}")
        String cep
) {
}
