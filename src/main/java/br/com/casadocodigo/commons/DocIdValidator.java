package br.com.casadocodigo.commons;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocIdValidator implements ConstraintValidator<DocId, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (new CPF(value).isValid() || new CNPJ(value).isValid());
    }
}