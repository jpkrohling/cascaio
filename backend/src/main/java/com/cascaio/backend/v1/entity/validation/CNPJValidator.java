package com.cascaio.backend.v1.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CNPJValidator implements ConstraintValidator<CNPJ, String> {
    @Override
    public void initialize(CNPJ constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
                || value.isEmpty()
                || value.matches("^\\d{14}$");
    }
}
