package com.cascaio.backend.v1.entity.adapters.api;

import com.cascaio.api.v1.SavingsAccountCreateRequest;
import com.cascaio.api.v1.SavingsAccountResponse;
import com.cascaio.api.v1.SavingsAccountUpdateRequest;
import com.cascaio.backend.v1.boundary.FinancialInstitutionService;
import com.cascaio.backend.v1.entity.SavingsAccount;

import javax.inject.Inject;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class SavingsAccountAdapter extends UserDataAdapter<SavingsAccountCreateRequest, SavingsAccountUpdateRequest, SavingsAccountResponse, SavingsAccount> {

    @Inject
    CheckingAccountAdapter checkingAccountAdapter;

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
    public SavingsAccount adaptUpdate(SavingsAccountUpdateRequest request) {
        return new SavingsAccount(checkingAccountAdapter.adaptUpdate(request));
    }

    @Override
    public SavingsAccount adaptCreate(SavingsAccountCreateRequest request) {
        return new SavingsAccount(checkingAccountAdapter.adaptCreate(request));
    }
}
