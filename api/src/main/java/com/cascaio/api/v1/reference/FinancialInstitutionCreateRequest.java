package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.validation.BIC;
import com.cascaio.api.v1.validation.Bankleitzahl;
import com.cascaio.api.v1.validation.CNPJ;
import com.cascaio.api.v1.validation.Country;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class FinancialInstitutionCreateRequest {
    @NotNull
    private String name;

    @NotNull
    @Country
    private String country; // 2 chars

    @CNPJ
    private String cnpj; // Brazilian CNPJ

    @Bankleitzahl
    private String bankleitzahl; // German BLZ

    @BIC
    private String bic; // as per ISO 9362

    private String codCompensacao; // Brazilian Codigo de Compensacao

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodCompensacao() {
        return codCompensacao;
    }

    public void setCodCompensacao(String codCompensacao) {
        this.codCompensacao = codCompensacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getBankleitzahl() {
        return bankleitzahl;
    }

    public void setBankleitzahl(String bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
