package com.cascaio.api.v1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ISODateValidator implements ConstraintValidator<ISODate, String> {
    @Override
    public void initialize(ISODate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
                || value.isEmpty()
                || value.matches("^\\d{4}-((0[1-9])|(1[012]))-((0[1-9]|[12]\\d)|3[01])$");
    }
}
