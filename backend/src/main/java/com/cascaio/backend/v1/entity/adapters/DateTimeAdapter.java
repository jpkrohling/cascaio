package com.cascaio.backend.v1.entity.adapters;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author jpkroehling
 *         2014-01-07
 */
public class DateTimeAdapter extends XmlAdapter<String, DateTime> {
    public DateTime unmarshal(String v) throws Exception {
        return new DateTime(v);
    }

    public String marshal(DateTime v) throws Exception {
        return v.toString();
    }
}
