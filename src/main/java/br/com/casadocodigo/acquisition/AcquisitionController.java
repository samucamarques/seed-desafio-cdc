package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

//Intrinsic cognitive load: 5
@RestController
@RequestMapping("/acquisition")
@RequiredArgsConstructor
public class AcquisitionController {

    //1
    private final AcquisitionRepository acquisitionRepository;
    //1
    private final BookRepository bookRepository;

    @InitBinder
    public void bind(WebDataBinder binder) {
        binder.addValidators(new TotalPriceValidator(bookRepository));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public URI create(
            /* 1 */ @RequestBody @Valid AcquisitionRequest request, UriComponentsBuilder uriBuilder) {

        //1
        final Acquisition acquisition = acquisitionRepository.save(request.toDomain(bookRepository::findAllById)); //1
        return uriBuilder.path("/detail/{id}").build(acquisition.getId());
    }
}
