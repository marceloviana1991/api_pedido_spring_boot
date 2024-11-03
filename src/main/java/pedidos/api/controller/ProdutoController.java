package pedidos.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.produto.DadosAtualizacaoProduto;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.DadosDetalhamentoProduto;
import pedidos.api.dto.produto.estoque.DadosCadastroProdutoEstoque;
import pedidos.api.service.entity.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@Valid @RequestBody DadosCadastroProduto dadosCadastroProduto,
                                              UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.cadastrar(dadosCadastroProduto, request);
        var uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(dadosDetalhamentoProduto.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoProduto);
    }

    @PostMapping("/estoque")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> adicionarEmEstoque(
            @Valid @RequestBody DadosCadastroProdutoEstoque dadosCadastroProdutoEstoque) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.adicionarEmEstoque(
                dadosCadastroProdutoEstoque);
        return ResponseEntity.ok(dadosDetalhamentoProduto);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoProduto>> listar(Pageable pageable) {
        List<DadosDetalhamentoProduto> dadosDetalhamentoProdutoList = produtoService.listar(pageable);
        return ResponseEntity.ok(dadosDetalhamentoProdutoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable Long id) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.detalhar(id);
        return ResponseEntity.ok(dadosDetalhamentoProduto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(
            @Valid @RequestBody DadosAtualizacaoProduto dadosAtualizacaoProduto, HttpServletRequest request) {
        DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.atualizar(dadosAtualizacaoProduto, request);
        return ResponseEntity.ok(dadosDetalhamentoProduto);
    }
}
