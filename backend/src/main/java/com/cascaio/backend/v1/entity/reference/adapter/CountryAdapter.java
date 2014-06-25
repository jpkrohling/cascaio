package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.CountryResponse;

import java.util.Locale;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CountryAdapter {

    public CountryResponse adapt(String countryCode) {
        Locale locale = new Locale("", countryCode);
        return new CountryResponse(locale.getCountry(), locale.getDisplayName());
    }

}
