package br.com.casadocodigo.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.DuplicateKeyException;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@AllArgsConstructor
@Getter
public class CreateAuthorRequest {
    @NotEmpty
    private final String name;
    @NotEmpty
    @Email
    private final String mailAddress;
    @NotEmpty
    @Length(min = 1, max = 400)
    private final String description;

    public Author toDomain(EntityManager entityManager) {
        entityManager
                .createQuery("select mailAddress from Author where mailAddress = :mailAddress", String.class)
                .setParameter("mailAddress", mailAddress)
                .getResultList()
                .stream()
                .findFirst()
                .ifPresent(mailAddressInDb -> {
                    throw new DuplicateKeyException("Mail address already exists for another user");
                });

        return new Author(this.name, this.mailAddress, this.description, Instant.now());
    }
}
