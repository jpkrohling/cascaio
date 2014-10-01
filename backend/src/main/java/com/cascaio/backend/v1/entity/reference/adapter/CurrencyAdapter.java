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
package com.cascaio.backend.v1.entity.reference.adapter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.joda.money.CurrencyUnit;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Converter(autoApply = true)
public class CurrencyAdapter implements AttributeConverter<CurrencyUnit, String> {
    public CurrencyUnit adapt(String currency) {
        if (null == currency || currency.isEmpty()) {
            return null;
        }
        return CurrencyUnit.of(currency);
    }

    public String adapt(CurrencyUnit currencyUnit) {
        if (null == currencyUnit) {
            return null;
        }
        return currencyUnit.getCurrencyCode();
    }

    @Override
    public String convertToDatabaseColumn(CurrencyUnit attribute) {
        return adapt(attribute);
    }

    @Override
    public CurrencyUnit convertToEntityAttribute(String dbData) {
        return adapt(dbData);
    }
}
