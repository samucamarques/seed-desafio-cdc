package br.com.casadocodigo.acquisition;

import br.com.casadocodigo.book.BookRepository;
import br.com.casadocodigo.country.CountryRepository;
import br.com.casadocodigo.coupon.CouponRepository;
import br.com.casadocodigo.state.StateRepository;
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

//Intrinsic cognitive load: 7
@RestController
@RequestMapping("/acquisition")
@RequiredArgsConstructor
public class AcquisitionController {

    //1
    private final AcquisitionRepository acquisitionRepository;
    //1
    private final BookRepository bookRepository;
    //1
    private final CouponRepository couponRepository;

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    @InitBinder
    public void bind(WebDataBinder binder) {
        binder.addValidators(new TotalPriceValidator(bookRepository));
        binder.addValidators(new CouponExpirationValidator(couponRepository));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public URI create(
            /* 1 */ @RequestBody @Valid AcquisitionRequest request, UriComponentsBuilder uriBuilder) {

        //1
        final Acquisition acquisition = acquisitionRepository.save(
                request.toDomain(
                        bookRepository::findAllById, //1
                        couponRepository::findByCode,
                        countryRepository::getOne,
                        stateRepository::getOne)); //1
        return acquisition.detailURI(uriBuilder);
    }
}
