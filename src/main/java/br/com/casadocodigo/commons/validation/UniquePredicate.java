package br.com.casadocodigo.commons.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UniquePredicateValidator.class)
public @interface UniquePredicate {

    String message() default "duplications not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String property();

    String category();
}
