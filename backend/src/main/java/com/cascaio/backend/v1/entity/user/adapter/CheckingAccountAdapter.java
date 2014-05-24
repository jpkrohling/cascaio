package com.cascaio.backend.v1.entity.user.adapter;

import com.cascaio.api.v1.user.CheckingAccountCreateRequest;
import com.cascaio.api.v1.user.CheckingAccountResponse;
import com.cascaio.api.v1.user.CheckingAccountUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.FinancialInstitutionService;
import com.cascaio.backend.v1.entity.user.CheckingAccount;
import com.cascaio.backend.v1.entity.reference.FinancialInstitution;
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
