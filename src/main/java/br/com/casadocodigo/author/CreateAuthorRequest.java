package br.com.casadocodigo.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String mailAddress;
    @NotEmpty
    @Length(min = 1, max = 400)
    private String description;

    public Author toDomain() {
        return new Author(this.name, this.mailAddress, this.description, Instant.now());
    }
}