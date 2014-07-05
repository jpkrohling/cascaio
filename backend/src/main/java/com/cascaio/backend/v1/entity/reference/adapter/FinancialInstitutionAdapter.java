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
        response.setId(financialInstitution.getId());
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
        financialInstitution.setBic(request.getBic());
        financialInstitution.setCnpj(request.getCnpj());
        financialInstitution.setCodCompensacao(request.getCodCompensacao());
        financialInstitution.setBankleitzahl(request.getBankleitzahl());

        return financialInstitution;
    }

    @Override
    public FinancialInstitution adaptCreate(FinancialInstitutionCreateRequest request) {
        FinancialInstitution financialInstitution = new FinancialInstitution(request.getName(), request.getCountry());
        financialInstitution.setBic(request.getBic());
        financialInstitution.setCnpj(request.getCnpj());
        financialInstitution.setCodCompensacao(request.getCodCompensacao());
        financialInstitution.setBankleitzahl(request.getBankleitzahl());
        return financialInstitution;
    }
}
