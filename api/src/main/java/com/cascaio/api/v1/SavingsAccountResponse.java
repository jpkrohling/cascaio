package com.cascaio.api.v1;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class SavingsAccountResponse extends CheckingAccountResponse {
    public SavingsAccountResponse() {
    }

    public SavingsAccountResponse(CheckingAccountResponse checkingAccountResponse) {
        setTotal(checkingAccountResponse.getTotal());
        setCurrencyUnit(checkingAccountResponse.getCurrencyUnit());
        setName(checkingAccountResponse.getName());
        setId(checkingAccountResponse.getId());
        setFinancialInstitutionId(checkingAccountResponse.getFinancialInstitutionId());
    }
}
