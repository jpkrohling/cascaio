package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.FinancialInstitutionCreateRequest;
import com.cascaio.api.v1.reference.FinancialInstitutionResponse;
import com.cascaio.api.v1.reference.FinancialInstitutionUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.FinancialInstitution;
import com.cascaio.backend.v1.entity.reference.adapter.FinancialInstitutionAdapter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/financialInstitutions")
@Stateless
public class FinancialInstitutionService extends BaseService<
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
