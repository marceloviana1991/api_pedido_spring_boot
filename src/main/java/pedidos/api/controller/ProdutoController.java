package pedidos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.DadosDetalhamentoProduto;
import pedidos.api.model.Produto;
import pedidos.api.repository.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public DadosDetalhamentoProduto cadastrar(@RequestBody DadosCadastroProduto dadosCadastroProduto) {
        Produto produto = new Produto(dadosCadastroProduto);
        produtoRepository.save(produto);
        return new DadosDetalhamentoProduto(produto);
    }

    @GetMapping
    public List<DadosDetalhamentoProduto> listar() {
        return produtoRepository.findAll().stream().map(DadosDetalhamentoProduto::new).toList();
    }
}
