package br.com.casadocodigo.commons.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CountryOwnershipValidator.class)
public @interface CountryOwnership {

    String message() default "{stateField} must be member of the {countryField}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String countryField();

    String stateField();
}
