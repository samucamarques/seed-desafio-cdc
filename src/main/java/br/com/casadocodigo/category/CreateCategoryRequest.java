package br.com.casadocodigo.category;

import br.com.casadocodigo.commons.validation.UniquePredicate;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

//Intrinsic cognitive load: 1
@Getter // for swagger to show the properties on request body example
public class CreateCategoryRequest {

    @NotEmpty
    @UniquePredicate(category = "category", property = "name")
    private final String name;

    @JsonCreator
    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    //1
    public Category toDomain() {
        return new Category(this.name);
    }
}
