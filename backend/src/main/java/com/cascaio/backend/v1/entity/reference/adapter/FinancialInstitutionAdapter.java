package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.FinancialInstitutionCreateRequest;
import com.cascaio.api.v1.reference.FinancialInstitutionResponse;
import com.cascaio.api.v1.reference.FinancialInstitutionUpdateRequest;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.FinancialInstitution;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class FinancialInstitutionAdapter extends
        EntityAdapter<FinancialInstitutionCreateRequest, FinancialInstitutionUpdateRequest, FinancialInstitutionResponse, FinancialInstitution> {

    @Override
    public FinancialInstitutionResponse adaptPersistent(FinancialInstitution financialInstitution) {
        FinancialInstitutionResponse response = new FinancialInstitutionResponse();
        response.setBankleitzahl(financialInstitution.getBankleitzahl());
        response.setBic(financialInstitution.getBic());
        response.setCnpj(financialInstitution.getCnpj());
        response.setCodCompensacao(financialInstitution.getCodCompensacao());
        response.setCountry(financialInstitution.getCountry());
        response.setName(financialInstitution.getName());
        return response;
    }

    @Override
    public FinancialInstitution adaptUpdate(FinancialInstitutionUpdateRequest request, FinancialInstitution financialInstitution) {
        String name = request.getName();
        if (isSet(name)) {
            financialInstitution.setName(name);
        }

        return adaptCommonRequest(request, financialInstitution);
    }

    @Override
    public FinancialInstitution adaptCreate(FinancialInstitutionCreateRequest request) {
        FinancialInstitution financialInstitution = new FinancialInstitution(request.getName(), request.getCountry());
        return adaptCommonRequest(request, financialInstitution);
    }

    private FinancialInstitution adaptCommonRequest(FinancialInstitutionCreateRequest request, FinancialInstitution financialInstitution) {
        financialInstitution.setBic(request.getBic());
        financialInstitution.setCnpj(request.getCnpj());
        financialInstitution.setCodCompensacao(request.getCodCompensacao());
        financialInstitution.setBankleitzahl(request.getBankleitzahl());
        return financialInstitution;
    }
}
