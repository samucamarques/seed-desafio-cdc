package br.com.casadocodigo.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckTotalPriceValidator implements ConstraintValidator<CheckTotalPrice, String> {
   public void initialize(CheckTotalPrice constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return false;
   }
}
