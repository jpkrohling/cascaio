package com.cascaio.api.v1;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CheckingAccountCreateRequest {
    // String id, CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution
    private String name;
    private String currencyUnit;
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
