package br.com.casadocodigo.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.DuplicateKeyException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.function.Predicate;

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

    public Author toDomain(Predicate<String> uniqueMailPredicate) {
        if (uniqueMailPredicate.test(mailAddress)) {
            throw new DuplicateKeyException("Mail address in use for another author");
        }

        return new Author(this.name, this.mailAddress, this.description, Instant.now());
    }
}
