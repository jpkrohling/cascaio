package com.cascaio.backend.v1.entity;

import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FinancialInstitutionAccount extends MoneyAccount {
    @ManyToOne
    @NotNull
    private FinancialInstitution financialInstitution;

    // JPA happy
    protected FinancialInstitutionAccount() {
    }

    public FinancialInstitutionAccount(CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(user, name, currency);
        this.financialInstitution = financialInstitution;
    }

    public FinancialInstitutionAccount(String id, CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(id, user, name, currency);
        this.financialInstitution = financialInstitution;
    }

    public void setFinancialInstitution(FinancialInstitution financialInstitution) {
        this.financialInstitution = financialInstitution;
    }

    public FinancialInstitution getFinancialInstitution() {
        return financialInstitution;
    }

}
