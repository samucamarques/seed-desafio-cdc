package br.com.casadocodigo;

import br.com.casadocodigo.author.AuthorController;
import br.com.casadocodigo.author.CreateAuthorRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorUnitTests {

    @Mock private EntityManager entityManager;
    private AuthorController authorController;

    @BeforeEach
    void contextLoads() {
        authorController = new AuthorController(entityManager);
    }

    @Test
    @DisplayName("Should not allow mailAccount duplications")
    public void test1() {
        final String mailAddressInput = "dummy@test.com";

        final TypedQuery typedQuery = mock(TypedQuery.class);

        when(entityManager.createQuery("select mailAddress from Author where mailAddress = :mailAddress", String.class))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter("mailAddress", mailAddressInput)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(mailAddressInput));

        Assertions.assertThrows(DuplicateKeyException.class,
                () -> authorController.create(new CreateAuthorRequest("dummy", mailAddressInput, "ensure validation")));

    }

}
