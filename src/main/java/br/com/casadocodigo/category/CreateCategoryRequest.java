package br.com.casadocodigo.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

@Getter // for swagger to show the properties on request body example
public class CreateCategoryRequest {
    @NotEmpty
    private final String name;

    @JsonCreator
    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public Category toDomain(Predicate<String> uniqueNamePredicate) {
        if (uniqueNamePredicate.test(name)) {
            throw new IllegalArgumentException("Name in use for another category");
        }

        return new Category(this.name);
    }
}
