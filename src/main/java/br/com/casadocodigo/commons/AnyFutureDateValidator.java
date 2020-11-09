package br.com.casadocodigo.commons;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;

public class AnyFutureDateValidator implements ConstraintValidator<AnyFutureDate, Instant> {

    @Override
    public boolean isValid(Instant value, ConstraintValidatorContext context) {
        return value.isAfter(Instant.now());
    }
}
