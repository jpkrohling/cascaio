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
package com.cascaio.backend.v1.entity.user.adapter;

import com.cascaio.api.v1.user.SavingsAccountCreateRequest;
import com.cascaio.api.v1.user.SavingsAccountResponse;
import com.cascaio.api.v1.user.SavingsAccountUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.FinancialInstitutionService;
import com.cascaio.backend.v1.entity.reference.FinancialInstitution;
import com.cascaio.backend.v1.entity.user.SavingsAccount;
import org.joda.money.CurrencyUnit;

import javax.inject.Inject;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class SavingsAccountAdapter extends UserDataAdapter<SavingsAccountCreateRequest, SavingsAccountUpdateRequest, SavingsAccountResponse, SavingsAccount> {

    @Inject
    FinancialInstitutionService financialInstitutionService;

    @Override
    public SavingsAccountResponse adaptPersistent(SavingsAccount savingsAccount) {
        SavingsAccountResponse response = new SavingsAccountResponse();
        response.setCurrencyUnit(savingsAccount.getCurrency().getCurrencyCode());
        response.setFinancialInstitutionId(savingsAccount.getFinancialInstitution().getId());
        response.setName(savingsAccount.getName());
        response.setId(savingsAccount.getId());
        response.setTotal(savingsAccount.getTotal());
        return response;
    }

    @Override
    public SavingsAccount adaptUpdate(SavingsAccountUpdateRequest request, SavingsAccount account) {
        account.setName(currentOrUpdated(request.getName(), account.getName()));

        String financialInstitutionId = request.getFinancialInstitutionId();
        if (isSet(financialInstitutionId)) {
            account.setFinancialInstitution(financialInstitutionService.readAsEntity(financialInstitutionId));
        }

        String currencyUnit = request.getCurrencyUnit();
        if (isSet(currencyUnit)) {
            account.setCurrency(CurrencyUnit.of(currencyUnit));
        }

        return account;
    }

    @Override
    public SavingsAccount adaptCreate(SavingsAccountCreateRequest request) {
        FinancialInstitution fi = financialInstitutionService.readAsEntity(request.getFinancialInstitutionId());
        CurrencyUnit currency = CurrencyUnit.of(request.getCurrencyUnit());
        return new SavingsAccount(getCascaioUser(), request.getName(), currency, fi);
    }
}
