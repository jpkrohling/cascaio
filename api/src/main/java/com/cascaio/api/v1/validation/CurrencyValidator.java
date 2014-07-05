/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cascaio.api.v1.validation;

import org.joda.money.CurrencyUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CurrencyValidator implements ConstraintValidator<Currency, String> {
    Logger logger = LoggerFactory.getLogger(CurrencyValidator.class);

    @Override
    public void initialize(Currency constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            logger.trace("Got an empty currency. Validation passed.");
            return true;
        }

        for (CurrencyUnit currency : CurrencyUnit.registeredCurrencies()) {
            if (currency.getCurrencyCode().equals(value)) {
                logger.trace("Found {} in the currency stack", value);
                return true;
            }

        }

        logger.trace("Couldn't find {} in the currency stack", value);
        return false;
    }
}
