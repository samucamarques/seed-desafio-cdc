package br.com.casadocodigo.state;

import br.com.casadocodigo.author.Author;
import br.com.casadocodigo.commons.validation.ExistsById;
import br.com.casadocodigo.commons.validation.UniquePredicate;
import br.com.casadocodigo.country.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.LongFunction;

//Intrinsic cognitive load: 1
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
public class CreateStateRequest {

    @NotEmpty
    @UniquePredicate(category = "state", property = "name")
    private final String name;

    @NotNull
    //@ExistsById(entityClass = Country.class)
    private final Long countryId;

    //1
    public State toDomain(
            LongFunction<Optional<Country>> findCountryById) {

        //1
        final Country country =
                findCountryById.apply(countryId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //1

        return new State(name, country);
    }

}
