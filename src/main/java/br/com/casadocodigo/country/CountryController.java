package br.com.casadocodigo.country;

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
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryRepository countryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> create(@Valid @RequestBody CreateCountryRequest request) {
        return countryRepository.save(request.toDomain()).toMap();
    }
}
