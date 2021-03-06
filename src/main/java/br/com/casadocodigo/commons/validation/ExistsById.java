package br.com.casadocodigo.commons.validation;

import br.com.casadocodigo.commons.contracts.CDCEntity;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ExistsByIdValidator.class)
public @interface ExistsById {

    String message() default "not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends CDCEntity> entityClass();
}
