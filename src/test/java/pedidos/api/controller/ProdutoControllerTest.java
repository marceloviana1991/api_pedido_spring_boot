package pedidos.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pedidos.api.repository.ProdutoRepository;
import pedidos.api.service.security.TokenService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Retorna 403 para USER")
    @WithMockUser(roles="USER")
    void cadastrarProdutoCenario1() throws Exception {
        var response = mvc.perform(post("/produtos")).andReturn().getResponse();
        Assertions.assertEquals(403, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 400 para ADMIN")
    @WithMockUser(roles="ADMIN")
    void cadastrarProdutoCenario2() throws Exception {
        var response = mvc.perform(post("/produtos")).andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());
    }


    @Test
    @DisplayName("Retorna 201 para produto cadastrado com sucesso")
    @WithMockUser(roles="ADMIN")
    void cadastrarProdutoCenario3() throws Exception {
        String json =
                """
                {
                    "nome": "produto",
                    "valor": 0.1
                }
                """;
        var response = mvc.perform(post("/produtos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 400 para erro de valodação nome vazio")
    @WithMockUser(roles="ADMIN")
    void cadastrarProdutoCenario4() throws Exception {
        String json =
                """
                {
                    "nome": "",
                    "valor": 0.1
                }
                """;
        var response = mvc.perform(post("/produtos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 400 para erro de valodação valor ausente")
    @WithMockUser(roles="ADMIN")
    void cadastrarProdutoCenario5() throws Exception {
        String json =
                """
                {
                    "nome": "produto"
                }
                """;
        var response = mvc.perform(post("/produtos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 400 para ADMIN")
    @WithMockUser(roles="ADMIN")
    void cadastrarProdutoCenario6() throws Exception {
        var response = mvc.perform(post("/produtos/estoque")).andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 403 para USER")
    @WithMockUser(roles="USER")
    void cadastrarProdutoCenario7() throws Exception {
        var response = mvc.perform(post("/produtos/estoque")).andReturn().getResponse();
        Assertions.assertEquals(403, response.getStatus());
    }

}