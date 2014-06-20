package com.cascaio.api.v1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CountryValidator implements ConstraintValidator<Country, String> {
    @Override
    public void initialize(Country constraintAnnotation) {
    }

    @Override
    public boolean isValid(String country, ConstraintValidatorContext context) {
        boolean validCountry = false;

        if (null == country || country.isEmpty()) {
            return true;
        }

        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.getCountry().equalsIgnoreCase(country)) {
                validCountry = true;
            }
        }

        return validCountry;
    }
}
