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

import com.cascaio.api.v1.reference.CountryResponse;
import com.cascaio.backend.v1.entity.reference.adapter.CountryAdapter;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/countries")
@Stateless
public class CountryService {

    @Inject
    CountryAdapter adapter;

    @GET
    @RolesAllowed({"admin", "user"})
    public List<CountryResponse> getCountries() {
        String[] countryCodes = Locale.getISOCountries();
        List<CountryResponse> countryResponseList = new ArrayList<CountryResponse>(countryCodes.length);
        for (String locale : countryCodes) {
            countryResponseList.add(adapter.adapt(locale));
        }

        return countryResponseList;
    }

}
