package com.cascaio.backend.v1.entity.adapters;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author jpkroehling
 *         2014-01-07
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String v) throws Exception {
        return new LocalDate(v);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
