package com.cascaio.backend.v1.entity.adapters;

import org.joda.money.CurrencyUnit;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * User: jpkroehling
 * Date: 2014-01-14
 * Time: 7:15 PM
 */
public class CurrencyUnitAdapter extends XmlAdapter<String, CurrencyUnit> {
    public CurrencyUnit unmarshal(String v) throws Exception {
        return CurrencyUnit.of(v);
    }

    public String marshal(CurrencyUnit v) throws Exception {
        return v.getCurrencyCode();
    }

}
