package pedidos.api.infra.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pedidos.api.infra.exception.ValidacaoException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class Disco {

    @Value("${contato.disco.raiz}")
    private String raiz;

    @Value("${contato.disco.diretorio-fotos}")
    private String diretorioFotos;

    @Async
    public CompletableFuture<String> salvarFoto(MultipartFile foto) throws IOException {
        System.out.println("Thread do salvar foto: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(salvar(this.diretorioFotos, foto));
    }

    public String salvar(String diretorio, MultipartFile arquivo) throws IOException {
        Path diretorioPath = Paths.get(this.raiz, diretorio);
        if (arquivo.getOriginalFilename() == null) {
            throw new ValidacaoException("Falha no nome do arquivo!");
        }
        String novoNome = this.gerarNovoNome(arquivo.getOriginalFilename());
        Path arquivoPath = diretorioPath.resolve(novoNome);
        Files.createDirectories(diretorioPath);
        arquivo.transferTo(arquivoPath.toFile());
        return novoNome;
    }

    private String gerarNovoNome(String nomeOriginal)
    {
        String[] nomeSplit = nomeOriginal.split("\\.");
        String extensao = "." + nomeSplit[1];
        return UUID.randomUUID() + extensao;
    }

    @Async
    public void excluirFoto(String nomeDaFoto) {
        System.out.println("Thread do excluir foto: " + Thread.currentThread().getName());
        Path diretorioPath = Paths.get(raiz, diretorioFotos);
        File file = new File(diretorioPath + "/" + nomeDaFoto);
        boolean deleted = file.delete();
        if (deleted) {
            System.out.println("Arquivo excluido com sucesso!");
        }
    }
}
