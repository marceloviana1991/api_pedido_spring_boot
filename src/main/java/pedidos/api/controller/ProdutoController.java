package pedidos.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedidos.api.dto.produto.DadosAtualizacaoProduto;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.DadosDetalhamentoProduto;
import pedidos.api.dto.produto.estoque.DadosCadastroProdutoEstoque;
import pedidos.api.model.Produto;
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
        Produto produto = produtoService.cadastrar(dadosCadastroProduto, request);
        var uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @PostMapping("/estoque")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> adicionarEmEstoque(
            @Valid @RequestBody DadosCadastroProdutoEstoque dadosCadastroProdutoEstoque) {
        Produto produto = produtoService.adicionarEmEstoque(dadosCadastroProdutoEstoque);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoProduto>> listar(Pageable pageable) {
        Page<Produto> produtoPage = produtoService.listar(pageable);
        return ResponseEntity.ok(produtoPage.stream().map(DadosDetalhamentoProduto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable Long id) {
        Produto produto = produtoService.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(
            @Valid @RequestBody DadosAtualizacaoProduto dadosAtualizacaoProduto, HttpServletRequest request) {
        Produto produto = produtoService.atualizar(dadosAtualizacaoProduto, request);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }
}
