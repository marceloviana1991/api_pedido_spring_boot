package pedidos.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

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

}