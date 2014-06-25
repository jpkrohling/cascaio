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
