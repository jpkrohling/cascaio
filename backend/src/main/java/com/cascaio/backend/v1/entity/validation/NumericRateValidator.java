package com.cascaio.backend.v1.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class NumericRateValidator implements ConstraintValidator<NumericRate, String> {
    @Override
    public void initialize(NumericRate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
                || value.isEmpty()
                || value.matches("^\\-?\\d+(\\.\\d{1,6})?$");
    }
}
