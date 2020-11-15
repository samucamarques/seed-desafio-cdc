package br.com.casadocodigo;

import br.com.casadocodigo.author.CreateAuthorRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
class AuthorUnitTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private Jackson2ObjectMapperBuilder mapperBuilder;

    @Test
    @DisplayName("Should not allow mailAccount duplications")
    @SneakyThrows
    public void test1() {
        final String mailAddressInput = "dummy@test.com";
        final CreateAuthorRequest request =
                new CreateAuthorRequest("dummy", mailAddressInput, "ensure validation");

        doAuthorPost(request)
                .andExpect(status().isOk());

        doAuthorPost(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("mailAddress: duplications not allowed"));
    }

    private ResultActions doAuthorPost(CreateAuthorRequest request) throws Exception {
        return mvc.perform(
                post("/author")
                        .content(mapperBuilder.build().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }

}
