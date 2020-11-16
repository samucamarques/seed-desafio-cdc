package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

//Intrinsic cognitive load: 3
@RestController
@RequestMapping("/flux/payment")
@RequiredArgsConstructor
public class AcquisitionController {

    //1
    private final AcquisitionRepository acquisitionRepository;
    private final BookRepository bookRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public URI create(
            /* 1 */ @RequestBody @Valid AcquisitionRequest request) {

        //1
        Acquisition acquisition = acquisitionRepository.save(request.toDomain(bookRepository::findAllById));
        return URI.create(String.format("/detail/%sd", acquisition.getId()));
    }
}
