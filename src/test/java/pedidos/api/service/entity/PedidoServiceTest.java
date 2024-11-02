package pedidos.api.service.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pedidos.api.model.Produto;
import pedidos.api.repository.ProdutoRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private Produto produto;

    @Test
    @DisplayName("Tenta retirar produto de estoque indisponível")
    void retirarProdutoEmEstoqueCenario1() {
        BDDMockito.given(produtoRepository.getReferenceById(1L)).willReturn(produto);
        BDDMockito.given(produto.getQuantidadeEmEstoque()).willReturn(4);
        Assertions.assertThrows(RuntimeException.class, () -> pedidoService.retirarProdutoEmEstoque(1L,
                5));
    }

    @Test
    @DisplayName("Retira produto de estoque disponível")
    void retirarProdutoEmEstoqueCenario2() {
        BDDMockito.given(produtoRepository.getReferenceById(1L)).willReturn(produto);
        BDDMockito.given(produto.getQuantidadeEmEstoque()).willReturn(6);
        Assertions.assertDoesNotThrow(() -> pedidoService.retirarProdutoEmEstoque(1L,
                5));
    }

}