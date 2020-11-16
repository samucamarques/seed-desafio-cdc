package br.com.casadocodigo.country;

import br.com.casadocodigo.commons.validation.UniquePredicate;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

//Intrinsic cognitive load: 1
@Getter // for swagger to show the properties on request body example
public class CreateCountryRequest {

    @NotEmpty
    @UniquePredicate(category = "country", property = "name")
    private String name;

    @JsonCreator
    public CreateCountryRequest(String name) {
        this.name = name;
    }

    //1
    public Country toDomain() {
        return new Country(name);
    }
}
