package com.cascaio.backend.v1.entity;

import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CheckingAccount extends FinancialInstitutionAccount {
    // JPA happy
    protected CheckingAccount() {
    }

    public CheckingAccount(CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(user, name, currency, financialInstitution);
    }

    public CheckingAccount(String id, CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(id, user, name, currency, financialInstitution);
    }
}
