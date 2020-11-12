package br.com.casadocodigo.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

//Intrinsic cognitive load: 3
@Getter // for swagger to show the properties on request body example
public class CreateCategoryRequest {
    @NotEmpty
    private final String name;

    @JsonCreator
    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    @SneakyThrows
    //1
    public Category toDomain(Predicate<String> uniqueNamePredicate) {
        final BindingResult errors = new BeanPropertyBindingResult(this, this.getClass().getSimpleName());
        if (uniqueNamePredicate.test(name)) {
            //1
            errors.rejectValue("name", null, "is in use for another category");
        }
        if (errors.hasErrors()) {
            //1
            throw new MethodArgumentNotValidException(null, errors);
        }

        return new Category(this.name);
    }
}
