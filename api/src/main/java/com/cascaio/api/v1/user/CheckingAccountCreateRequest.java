package com.cascaio.api.v1.user;

import com.cascaio.api.v1.validation.Currency;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CheckingAccountCreateRequest {
    @NotNull
    private String name;

    @NotNull
    @Currency
    private String currencyUnit;

    @NotNull
    private String financialInstitutionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public String getFinancialInstitutionId() {
        return financialInstitutionId;
    }

    public void setFinancialInstitutionId(String financialInstitutionId) {
        this.financialInstitutionId = financialInstitutionId;
    }

    @Override
    public String toString() {
        return "CheckingAccountCreateRequest{" +
                "name='" + name + '\'' +
                ", currencyUnit='" + currencyUnit + '\'' +
                ", financialInstitutionId='" + financialInstitutionId + '\'' +
                '}';
    }
}
