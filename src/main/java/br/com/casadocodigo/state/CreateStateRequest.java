package br.com.casadocodigo.state;

import br.com.casadocodigo.commons.validation.ExistsById;
import br.com.casadocodigo.commons.validation.UniquePredicate;
import br.com.casadocodigo.country.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.function.LongFunction;

//Intrinsic cognitive load: 1
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
public class CreateStateRequest {

    @NotEmpty
    @UniquePredicate(category = "state", property = "name")
    private final String name;

    @NotNull
    @ExistsById(entityClass = Country.class)
    private final Long countryId;

    //1
    public State toDomain(
            LongFunction<Country> getReference) {

        //1
        final Country country = getReference.apply(countryId); //1
        if (country == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new State(name, country);
    }

}
