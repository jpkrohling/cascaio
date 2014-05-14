package com.cascaio.backend.v1.entity.adapters.api;

import com.cascaio.api.v1.CheckingAccountCreateRequest;
import com.cascaio.api.v1.CheckingAccountResponse;
import com.cascaio.api.v1.CheckingAccountUpdateRequest;
import com.cascaio.backend.v1.boundary.FinancialInstitutionService;
import com.cascaio.backend.v1.entity.CheckingAccount;
import com.cascaio.backend.v1.entity.FinancialInstitution;
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
    public CheckingAccount adaptUpdate(CheckingAccountUpdateRequest request) {
        FinancialInstitution fi = financialInstitutionService.readAsEntity(request.getFinancialInstitutionId());
        CurrencyUnit currency = CurrencyUnit.of(request.getCurrencyUnit());
        return new CheckingAccount(request.getId(), getCascaioUser(), request.getName(), currency, fi);
    }

    @Override
    public CheckingAccount adaptCreate(CheckingAccountCreateRequest request) {
        FinancialInstitution fi = financialInstitutionService.readAsEntity(request.getFinancialInstitutionId());
        CurrencyUnit currency = CurrencyUnit.of(request.getCurrencyUnit());
        return new CheckingAccount(getCascaioUser(), request.getName(), currency, fi);
    }


}
