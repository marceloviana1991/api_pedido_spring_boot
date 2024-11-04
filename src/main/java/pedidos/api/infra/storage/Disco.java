package pedidos.api.infra.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class Disco {

    @Value("${contato.disco.raiz}")
    private String raiz;

    @Value("${contato.disco.diretorio-fotos}")
    private String diretorioFotos;

    public String salvarFoto(MultipartFile foto) {
        String nomeDoArquivo = this.salvar(this.diretorioFotos, foto);
        return nomeDoArquivo;
    }

    public String salvar(String diretorio, MultipartFile arquivo) {
        Path diretorioPath = Paths.get(this.raiz, diretorio);
        String novoNome = this.gerarNovoNome(arquivo.getOriginalFilename());
        Path arquivoPath = diretorioPath.resolve(novoNome);

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
        return novoNome;
    }

    private String gerarNovoNome(String nomeOriginal)
    {
        String[] nomeSplit = nomeOriginal.split("\\.");
        String extensao = "." + nomeSplit[1];

        return UUID.randomUUID() + extensao;
    }
}