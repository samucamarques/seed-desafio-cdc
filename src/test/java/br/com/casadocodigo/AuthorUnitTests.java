package br.com.casadocodigo;

import br.com.casadocodigo.author.AuthorController;
import br.com.casadocodigo.author.AuthorRepository;
import br.com.casadocodigo.author.CreateAuthorRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorUnitTests {

    @Mock private AuthorRepository authorRepository;
    private AuthorController authorController;

    @BeforeEach
    void contextLoads() {
        authorController = new AuthorController(authorRepository);
    }

    @Test
    @DisplayName("Should not allow mailAccount duplications")
    public void test1() {
        final String mailAddressInput = "dummy@test.com";
        when(authorRepository.existsByMailAddress(mailAddressInput)).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(DuplicateKeyException.class,
                () -> authorController.create(new CreateAuthorRequest("dummy", mailAddressInput, "ensure validation")));

    }

}
