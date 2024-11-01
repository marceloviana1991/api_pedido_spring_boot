package pedidos.api.controller;

import jakarta.servlet.http.HttpServletRequest;
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
import pedidos.api.model.Usuario;
import pedidos.api.repository.ProdutoRepository;
import pedidos.api.service.entity.ProdutoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private HttpServletRequest request;

    @MockBean
    private Usuario usuario;

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
        given(produtoService.capturarUsuarioLogado(request)).willReturn(usuario);
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
        given(produtoService.capturarUsuarioLogado(request)).willReturn(usuario);
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
        given(produtoService.capturarUsuarioLogado(request)).willReturn(usuario);
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


}