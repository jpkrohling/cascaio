package com.cascaio.backend.v1.entity.reference.adapter;

import org.joda.money.CurrencyUnit;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CurrencyAdapter {
    public CurrencyUnit adapt(String currency) {
        return CurrencyUnit.of(currency);
    }

    public String adapt(CurrencyUnit currencyUnit) {
        return currencyUnit.getCurrencyCode();
    }
}
