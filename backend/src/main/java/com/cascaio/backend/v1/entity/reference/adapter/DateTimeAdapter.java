package com.cascaio.backend.v1.entity.reference.adapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import javax.inject.Inject;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class DateTimeAdapter {

    @Inject
    DateTimeFormatter formatter;

    public DateTime adapt(String dateTime) {
        return new DateTime(dateTime);
    }

    public String adapt(DateTime dateTime) {
        return dateTime.toString(formatter);
    }

}
