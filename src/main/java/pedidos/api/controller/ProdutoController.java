package pedidos.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.DadosMensagemGenerica;
import pedidos.api.dto.produto.DadosAtualizacaoProduto;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.DadosDetalhamentoProduto;
import pedidos.api.dto.produto.estoque.DadosCadastroProdutoEstoque;
import pedidos.api.service.entity.ProdutoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoProduto> cadastrarProduto(@Valid @RequestBody DadosCadastroProduto dadosCadastroProduto,
                                              UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.cadastrar(dadosCadastroProduto, request);
        var uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(dadosDetalhamentoProduto.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoProduto);
    }

    @PostMapping("/estoque")
    public ResponseEntity<DadosDetalhamentoProduto> adicionarProdutoEmEstoque(
            @Valid @RequestBody DadosCadastroProdutoEstoque dadosCadastroProdutoEstoque) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.adicionarEmEstoque(
                dadosCadastroProdutoEstoque);
        return ResponseEntity.ok(dadosDetalhamentoProduto);
    }

    @PostMapping("/foto/{id}")
    public ResponseEntity<?> uploadDeFotoDeProduto(@RequestParam MultipartFile foto, @PathVariable Long id) {
        try {
            System.out.println("Thread do controller: " + Thread.currentThread().getName());
            DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.adicionarFoto(foto, id);
            return ResponseEntity.ok(dadosDetalhamentoProduto);
        } catch (IOException ioException) {
            return ResponseEntity.badRequest().body(new DadosMensagemGenerica(ioException.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoProduto>> listarProdutos(Pageable pageable) {
        List<DadosDetalhamentoProduto> dadosDetalhamentoProdutoList = produtoService.listar(pageable);
        return ResponseEntity.ok(dadosDetalhamentoProdutoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalharProduto(@PathVariable Long id) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.detalhar(id);
        return ResponseEntity.ok(dadosDetalhamentoProduto);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoProduto> atualizarProduto(
            @Valid @RequestBody DadosAtualizacaoProduto dadosAtualizacaoProduto, HttpServletRequest request) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.atualizar(dadosAtualizacaoProduto, request);
        return ResponseEntity.ok(dadosDetalhamentoProduto);
    }
}
