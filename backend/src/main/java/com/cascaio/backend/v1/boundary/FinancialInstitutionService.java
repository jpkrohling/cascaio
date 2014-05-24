package com.cascaio.backend.v1.boundary;

import com.cascaio.api.v1.FinancialInstitutionCreateRequest;
import com.cascaio.api.v1.FinancialInstitutionResponse;
import com.cascaio.api.v1.FinancialInstitutionUpdateRequest;
import com.cascaio.backend.v1.entity.FinancialInstitution;
import com.cascaio.backend.v1.entity.adapters.api.FinancialInstitutionAdapter;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/financialInstitutions")
@Stateless
@SecurityDomain("keycloak")
public class FinancialInstitutionService extends
        BaseService<
                FinancialInstitutionCreateRequest,
                FinancialInstitutionUpdateRequest,
                FinancialInstitutionResponse,
                FinancialInstitution,
                FinancialInstitutionAdapter> {

    @Inject
    FinancialInstitutionAdapter adapter;

    @Override
    public FinancialInstitutionAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<FinancialInstitution> getPersistentClass() {
        return FinancialInstitution.class;
    }
}
