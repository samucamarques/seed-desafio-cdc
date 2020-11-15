package br.com.casadocodigo.commons;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocIdValidator implements ConstraintValidator<DocId, String> {

    private final CPFValidator cpfValidator = new CPFValidator();
    private final CNPJValidator cnpjValidator = new CNPJValidator();

    @Override
    public void initialize(DocId constraintAnnotation) {
        cpfValidator.initialize(constraintAnnotation.cpf());
        cnpjValidator.initialize(constraintAnnotation.cnpj());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return cpfValidator.isValid(value, context) || cnpjValidator.isValid(value, context);
    }
}