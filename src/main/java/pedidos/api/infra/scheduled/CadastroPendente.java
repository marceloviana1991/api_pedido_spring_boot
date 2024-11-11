package pedidos.api.infra.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pedidos.api.model.Usuario;
import pedidos.api.model.UsuarioVerificador;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.repository.UsuarioVerificadorRepository;

import java.util.List;

@Component
public class CadastroPendente {

    @Autowired
    private UsuarioVerificadorRepository usuarioVerificadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Scheduled(cron = "0 0 5 * * *")
    public void excluirTodosCadastrosPendentes() {
        List<UsuarioVerificador> verificadorList = usuarioVerificadorRepository.findAll();
        verificadorList.forEach(verificador -> {
            Usuario usuario = verificador.getUsuario();
            usuarioVerificadorRepository.delete(verificador);
            usuarioRepository.delete(usuario);
        });
    }
}
