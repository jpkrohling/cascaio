package com.cascaio.backend.v1.entity.adapter;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class DateTimeAdapter extends XmlAdapter<String, DateTime> {
    public DateTime unmarshal(String v) throws Exception {
        return new DateTime(v);
    }

    public String marshal(DateTime v) throws Exception {
        return v.toString();
    }
}
