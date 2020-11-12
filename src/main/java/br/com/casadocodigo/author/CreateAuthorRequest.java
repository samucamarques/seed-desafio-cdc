package br.com.casadocodigo.author;

import br.com.casadocodigo.commons.UniquePredicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

//Intrinsic cognitive load: 1
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
public class CreateAuthorRequest {
    @NotEmpty
    private final String name;
    @NotEmpty
    @Email
    @UniquePredicate(property = "mailAddress")
    private final String mailAddress;
    @NotEmpty
    @Length(min = 1, max = 400)
    private final String description;

    @SneakyThrows
    //1
    public Author toDomain() {
        return new Author(this.name, this.mailAddress, this.description);
    }
}
