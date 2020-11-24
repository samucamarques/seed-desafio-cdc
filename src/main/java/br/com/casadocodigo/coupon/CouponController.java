package br.com.casadocodigo.coupon;

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
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> create(@Valid @RequestBody CreateCouponRequest request) {
        return repository.save(request.toDomain()).toMap();
    }
}
