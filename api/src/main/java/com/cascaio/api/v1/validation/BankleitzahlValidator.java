package com.cascaio.api.v1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class BankleitzahlValidator implements ConstraintValidator<Bankleitzahl, String> {
    @Override
    public void initialize(Bankleitzahl constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
                || value.isEmpty()
                || value.matches("^\\d{8}$");
    }
}
