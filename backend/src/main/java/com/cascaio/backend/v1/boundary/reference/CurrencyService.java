package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.CurrencyResponse;
import org.joda.money.CurrencyUnit;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/currencies")
@Stateless
public class CurrencyService {

    @GET
    @RolesAllowed({"admin", "user"})
    public List<CurrencyResponse> getCurrencies() {
        List<CurrencyUnit> availableCurrencies = CurrencyUnit.registeredCurrencies();
        List<CurrencyResponse> currencyResponseList = new ArrayList<CurrencyResponse>(availableCurrencies.size());
        for (CurrencyUnit currency : availableCurrencies) {
            currencyResponseList.add(new CurrencyResponse(currency.getCurrencyCode()));
        }

        return currencyResponseList;
    }


}
