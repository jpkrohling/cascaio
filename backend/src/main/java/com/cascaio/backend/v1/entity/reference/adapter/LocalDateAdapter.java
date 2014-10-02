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

import com.cascaio.backend.v1.control.LocalDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
@Converter(autoApply = true)
public class LocalDateAdapter implements AttributeConverter<LocalDate, Date> {

    @Inject @LocalDateFormat
    DateTimeFormatter formatter;

    public LocalDate adapt(String date) {
        if (null == date || date.isEmpty()) {
            return null;
        }

        if (date.contains(".")) {
            String dateTimeParts[] = date.substring(0, 10).split("\\.");
            date = dateTimeParts[2] + "-" + dateTimeParts[1] + "-" + dateTimeParts[0];
        }

        return LocalDate.parse(date, formatter);
    }

    public String adapt(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }

        return localDate.format(formatter);
    }

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        return Date.valueOf(attribute);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        return dbData.toLocalDate();
    }

}
