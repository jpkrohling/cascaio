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

import com.cascaio.api.v1.user.CheckingAccountCreateRequest;
import com.cascaio.api.v1.user.CheckingAccountResponse;
import com.cascaio.api.v1.user.CheckingAccountUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.FinancialInstitutionService;
import com.cascaio.backend.v1.entity.reference.FinancialInstitution;
import com.cascaio.backend.v1.entity.user.CheckingAccount;
import org.joda.money.CurrencyUnit;

import javax.inject.Inject;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CheckingAccountAdapter extends
        UserDataAdapter<CheckingAccountCreateRequest, CheckingAccountUpdateRequest, CheckingAccountResponse, CheckingAccount> {

    @Inject
    FinancialInstitutionService financialInstitutionService;

    @Override
    public CheckingAccountResponse adaptPersistent(CheckingAccount checkingAccount) {
        CheckingAccountResponse response = new CheckingAccountResponse();
        response.setCurrencyUnit(checkingAccount.getCurrency().getCurrencyCode());
        response.setFinancialInstitutionId(checkingAccount.getFinancialInstitution().getId());
        response.setName(checkingAccount.getName());
        response.setId(checkingAccount.getId());
        response.setTotal(checkingAccount.getTotal());
        return response;
    }

    @Override
    public CheckingAccount adaptUpdate(CheckingAccountUpdateRequest request, CheckingAccount account) {
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
    public CheckingAccount adaptCreate(CheckingAccountCreateRequest request) {
        FinancialInstitution fi = financialInstitutionService.readAsEntity(request.getFinancialInstitutionId());
        CurrencyUnit currency = CurrencyUnit.of(request.getCurrencyUnit());
        return new CheckingAccount(getCascaioUser(), request.getName(), currency, fi);
    }
}
