package br.com.casadocodigo.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

//Intrinsic cognitive load: 3
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

    @SneakyThrows
    //1
    public Author toDomain(Predicate<String> uniqueMailPredicate) {
        final BindingResult errors = new BeanPropertyBindingResult(this, this.getClass().getSimpleName());
        if (uniqueMailPredicate.test(mailAddress)) {
            //1
            errors.rejectValue("mailAddress", null, "is in use for another author");
        }
        if (errors.hasErrors()) {
            //1
            throw new MethodArgumentNotValidException(null, errors);
        }

        return new Author(this.name, this.mailAddress, this.description);
    }
}
