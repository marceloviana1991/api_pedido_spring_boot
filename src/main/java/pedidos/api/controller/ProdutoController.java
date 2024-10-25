package pedidos.api.controller;

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
import pedidos.api.dto.produto.estoque.DadosDetalhamentoProdutoEstoque;
import pedidos.api.model.Produto;
import pedidos.api.model.ProdutoEstoque;
import pedidos.api.repository.ProdutoEstoqueRepository;
import pedidos.api.repository.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoEstoqueRepository produtoEstoqueRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@Valid @RequestBody DadosCadastroProduto dadosCadastroProduto,
                                              UriComponentsBuilder uriComponentsBuilder) {
        Produto produto = new Produto(dadosCadastroProduto);
        produtoRepository.save(produto);
        ProdutoEstoque produtoEstoque = new ProdutoEstoque(produto);
        produtoEstoqueRepository.save(produtoEstoque);
        var uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @PostMapping("/estoque")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProdutoEstoque> adicionarEmEstoque(
            @Valid @RequestBody DadosCadastroProdutoEstoque dadosCadastroProdutoEstoque) {
        Produto produto = produtoRepository.getReferenceById(dadosCadastroProdutoEstoque.idProduto());
        ProdutoEstoque produtoEstoque = produtoEstoqueRepository.getReferenceByProduto(produto);
        produtoEstoque.adicionarEmEstoque(dadosCadastroProdutoEstoque.quantidade());
        return ResponseEntity.ok(new DadosDetalhamentoProdutoEstoque(produtoEstoque));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoProduto>> listar(Pageable pageable) {
        return ResponseEntity.ok(produtoRepository.findAll(pageable).stream()
                .map(DadosDetalhamentoProduto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produtoRepository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(
            @Valid @RequestBody DadosAtualizacaoProduto dadosAtualizacaoProduto) {
        Produto produto = produtoRepository.getReferenceById(dadosAtualizacaoProduto.id());
        produto.atualizarDados(dadosAtualizacaoProduto);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }
}
