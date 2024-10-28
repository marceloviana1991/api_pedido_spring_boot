package pedidos.api.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

@Table(name = "verificadores")
@Entity(name = "UsuarioVerificador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioVerificador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;
    private Instant dataExpiracao;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public UsuarioVerificador(Usuario usuario) {
        this.usuario = usuario;
        this.uuid = UUID.randomUUID();
        this.dataExpiracao = Instant.now().plusSeconds(600);
    }
}
