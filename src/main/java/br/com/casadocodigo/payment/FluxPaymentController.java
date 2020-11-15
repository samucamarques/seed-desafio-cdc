package br.com.casadocodigo.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//Intrinsic cognitive load: 3
@RestController
@RequestMapping("/flux/payment")
@RequiredArgsConstructor
public class FluxPaymentController {

    //1
    private final FluxPaymentRepository fluxPaymentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long create(
            /* 1 */ @RequestBody @Valid FluxPaymentRequest request) {

        //1
        FluxPayment fluxCreated = fluxPaymentRepository.save(request.toDomain());
        return fluxCreated.getId();
    }
}
