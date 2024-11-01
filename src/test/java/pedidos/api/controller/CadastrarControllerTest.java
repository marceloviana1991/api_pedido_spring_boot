package pedidos.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pedidos.api.repository.UsuarioRepository;
import pedidos.api.service.entity.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class CadastrarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Retorna 200 para pré-cadastro")
    void cadastrarUsuarioCenario1() throws Exception {
        //ARRANGE
        String json =
                """
                {
                    "login": "usuario",
                    "senha": "123",
                    "email": "usuario@email.com"
                }
                """;

        //ACT
        var response = mvc.perform(post("/cadastrar")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 400 para erro de validação para senha ausente")
    void cadastrarUsuarioCenario2() throws Exception {
        //ARRANGE
        String json =
                """
                {
                    "login": "usuario",
                    "email": "usuario@email.com"
                }
                """;

        //ACT
        var response = mvc.perform(post("/cadastrar")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 400 para erro de validação para login em branco")
    void cadastrarUsuarioCenario3() throws Exception {
        //ARRANGE
        String json =
                """
                {
                    "login": "",
                    "senha": "123",
                    "email": "usuario@email.com"
                }
                """;

        //ACT
        var response = mvc.perform(post("/cadastrar")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Retorna 400 para erro de validação para email fora do padrao")
    void cadastrarUsuarioCenario4() throws Exception {
        //ARRANGE
        String json =
                """
                {
                    "login": "usuario",
                    "senha": "123",
                    "email": "usuario"
                }
                """;

        //ACT
        var response = mvc.perform(post("/cadastrar")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }


}