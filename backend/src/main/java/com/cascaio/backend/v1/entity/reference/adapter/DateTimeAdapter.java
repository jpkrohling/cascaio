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

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class DateTimeAdapter {

    @Inject
    DateTimeFormatter formatter;

    public DateTime adaptToDateTime(String dateTime) {
        return new DateTime(dateTime);
    }

    public LocalDate adaptToLocalDate(String dateTime) {

        if (null != dateTime && !dateTime.isEmpty() && dateTime.contains(".")) {
            String dateTimeParts[] = dateTime.substring(0, 10).split("\\.");
            dateTime = dateTimeParts[2] + "-" + dateTimeParts[1] + "-" + dateTimeParts[0];
        }

        return new LocalDate(dateTime);
    }

    public String adapt(DateTime dateTime) {
        return dateTime.toString(formatter);
    }

    public String adapt(Date date) {
        return new DateTime(date.getTime()).toString(formatter);
    }

    public String adapt(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        return localDate.toString();
    }

}
