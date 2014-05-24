package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.ExchangeRateCreateRequest;
import com.cascaio.api.v1.reference.ExchangeRateResponse;
import com.cascaio.api.v1.reference.ExchangeRateUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.ExchangeRate;
import com.cascaio.backend.v1.entity.reference.adapter.ExchangeRateAdapter;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/exchangeRates")
@Stateless
@SecurityDomain("keycloak")
public class ExchangeRateService extends BaseService<
        ExchangeRateCreateRequest,
        ExchangeRateUpdateRequest,
        ExchangeRateResponse,
        ExchangeRate,
        ExchangeRateAdapter> {

    @Inject
    ExchangeRateAdapter adapter;

    @Override
    public ExchangeRateAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<ExchangeRate> getPersistentClass() {
        return ExchangeRate.class;
    }
}
