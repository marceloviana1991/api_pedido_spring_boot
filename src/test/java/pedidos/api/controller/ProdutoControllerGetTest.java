package pedidos.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pedidos.api.repository.ProdutoRepository;
import pedidos.api.service.entity.PedidoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerGetTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    @DisplayName("Retorna 200 para USER")
    @WithMockUser(roles="USER")
    void listarProdutosCenario1() throws Exception {
        var response = mvc.perform(get("/produtos")).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 403 para acesso sem autenticação")
    void listarProdutosCenario2() throws Exception {
        var response = mvc.perform(get("/produtos")).andReturn().getResponse();
        Assertions.assertEquals(403, response.getStatus());
    }
}
