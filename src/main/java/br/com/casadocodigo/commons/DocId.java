package br.com.casadocodigo.commons;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

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
@Constraint(validatedBy = DocIdValidator.class)
public @interface DocId {

    String message() default "must be a valid document id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CPF cpf();

    CNPJ cnpj();
}
