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

import org.slf4j.Logger;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class BigDecimalAdapter {

    @Inject
    Logger logger;

    public BigDecimal adapt(String value) {
        if (null == value || value.isEmpty()) {
            return null;
        }

        int lastComma = value.lastIndexOf(',');
        int lastDot = value.lastIndexOf('.');

        logger.trace("Raw value: {}", value);
        value = value.replaceAll("\\s.*", "");
        logger.trace("Cleaned up value: {}", value);

        // first, figure out which char is used as decimal separator.
        // the idea is that the number can be:
        // 1.234,45 or 1,234.45 to represent 1234.45
        if (lastComma > lastDot) {
            logger.trace("Decimal separator for {} is comma", value);
            value = value.replaceAll("[^\\d,-]", "");
            logger.trace("After first replace: {}", value);
            value = value.replaceAll(",", ".");
        } else {
            logger.trace("Decimal separator for {} is dot", value);
            value = value.replaceAll("[^\\d\\.-]", "");
        }
        logger.trace("String to determineSeparatorAndParseToBigDecimal into big decimal after normalization: {}", value);

        return new BigDecimal(value);
    }

    public String adapt(BigDecimal value) {
        if (null == value) {
            return null;
        }
        return value.toPlainString();
    }

}
