package br.com.casadocodigo.state;

import br.com.casadocodigo.country.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/state")
@RequiredArgsConstructor
public class StateController {

    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> create(@Valid @RequestBody CreateStateRequest request) {
        return stateRepository.save(request.toDomain(countryRepository::findById)).toMap();
    }
}
