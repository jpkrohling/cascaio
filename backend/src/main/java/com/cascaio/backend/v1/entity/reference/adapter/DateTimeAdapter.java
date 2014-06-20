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
        return localDate.toString();
    }

}
