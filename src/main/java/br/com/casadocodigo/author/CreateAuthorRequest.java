package br.com.casadocodigo.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
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
            throw new IllegalArgumentException("Mail address in use for another author");
        }

        return new Author(this.name, this.mailAddress, this.description);
    }
}
