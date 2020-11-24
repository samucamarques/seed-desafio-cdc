package br.com.casadocodigo.coupon;

import br.com.casadocodigo.commons.validation.AnyFutureDate;
import br.com.casadocodigo.commons.validation.UniquePredicate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
public class CreateCouponRequest {

    @NotBlank
    @UniquePredicate(category = "coupon", property = "code")
    private String code;

    @NotNull
    @Positive
    @DecimalMax("100.0")
    private BigDecimal discount;

    @AnyFutureDate
    private Instant expirationDate;

    public Coupon toDomain() {
        return new Coupon(code, discount, expirationDate);
    }
}
