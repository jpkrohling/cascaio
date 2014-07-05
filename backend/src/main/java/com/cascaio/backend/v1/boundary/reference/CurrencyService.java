/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
