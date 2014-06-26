package com.cascaio.backend.v1.entity.reference;

import com.cascaio.api.v1.validation.BIC;
import com.cascaio.api.v1.validation.Bankleitzahl;
import com.cascaio.api.v1.validation.CNPJ;
import com.cascaio.api.v1.validation.Country;
import com.cascaio.backend.v1.entity.NamedCascaioEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class FinancialInstitution extends NamedCascaioEntity {

    private String codCompensacao; // Brazilian Codigo de Compensacao

    @CNPJ
    private String cnpj; // Brazilian CNPJ

    @Bankleitzahl
    private String bankleitzahl; // German BLZ

    @BIC
    private String bic; // as per ISO 9362

    @NotNull
    @Country
    private String country; // 2 chars

    // JPA happy
    protected FinancialInstitution() {
    }

    public FinancialInstitution(String name, String country) {
        super(name);
        this.country = country;
    }

    public FinancialInstitution(String id, String name, String country) {
        super(id, name);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBankleitzahl() {
        return bankleitzahl;
    }

    public void setBankleitzahl(String bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodCompensacao() {
        return codCompensacao;
    }

    public void setCodCompensacao(String codCompensacao) {
        this.codCompensacao = codCompensacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinancialInstitution)) return false;

        FinancialInstitution that = (FinancialInstitution) o;

        if (bankleitzahl != null ? !bankleitzahl.equals(that.bankleitzahl) : that.bankleitzahl != null) return false;
        if (bic != null ? !bic.equals(that.bic) : that.bic != null) return false;
        if (cnpj != null ? !cnpj.equals(that.cnpj) : that.cnpj != null) return false;
        if (codCompensacao != null ? !codCompensacao.equals(that.codCompensacao) : that.codCompensacao != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codCompensacao != null ? codCompensacao.hashCode() : 0;
        result = 31 * result + (cnpj != null ? cnpj.hashCode() : 0);
        result = 31 * result + (bankleitzahl != null ? bankleitzahl.hashCode() : 0);
        result = 31 * result + (bic != null ? bic.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
