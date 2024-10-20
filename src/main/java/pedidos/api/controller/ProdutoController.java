package pedidos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pedidos.api.dto.produto.DadosCadastroProduto;
import pedidos.api.dto.produto.DadosDetalhamentoProduto;
import pedidos.api.model.Produto;
import pedidos.api.repository.ProdutoRepository;

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
}
