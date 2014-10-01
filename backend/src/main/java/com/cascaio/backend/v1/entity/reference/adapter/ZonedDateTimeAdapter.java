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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
@Converter(autoApply = true)
public class ZonedDateTimeAdapter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Inject
    DateTimeFormatter formatter;

    public ZonedDateTime adapt(String dateTime) {
        if (null == dateTime || dateTime.isEmpty()) {
            return null;
        }

        return ZonedDateTime.parse(dateTime, formatter);
    }

    public String adapt(ZonedDateTime dateTime) {
        if (null == dateTime) {
            return null;
        }

        return dateTime.format(formatter);
    }

    public String adapt(Date date) {
        if (null == date) {
            return null;
        }
        return ZonedDateTime
                .of(
                        LocalDateTime.ofEpochSecond(date.getTime(), 0, ZoneOffset.UTC),
                        ZoneOffset.UTC
                )
                .format(formatter);
    }

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        return Timestamp.valueOf(attribute.toLocalDateTime());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        return ZonedDateTime.of(dbData.toLocalDateTime(), ZoneOffset.UTC);
    }
}
