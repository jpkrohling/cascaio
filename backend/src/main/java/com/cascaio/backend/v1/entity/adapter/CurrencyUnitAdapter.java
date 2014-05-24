package com.cascaio.backend.v1.entity.adapter;

import org.joda.money.CurrencyUnit;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CurrencyUnitAdapter extends XmlAdapter<String, CurrencyUnit> {
    public CurrencyUnit unmarshal(String v) throws Exception {
        return CurrencyUnit.of(v);
    }

    public String marshal(CurrencyUnit v) throws Exception {
        return v.getCurrencyCode();
    }

}
