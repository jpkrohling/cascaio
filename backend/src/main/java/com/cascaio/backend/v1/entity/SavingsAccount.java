package com.cascaio.backend.v1.entity;

import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class SavingsAccount extends FinancialInstitutionAccount {
    public SavingsAccount() {
    }

    /**
     * Duplicates a CheckingAccount into a SavingsAccount
     * @param checkingAccount
     */
    public SavingsAccount(CheckingAccount checkingAccount) {
        super(checkingAccount.getUser(), checkingAccount.getName(), checkingAccount.getCurrency(), checkingAccount.getFinancialInstitution());
    }

    public SavingsAccount(CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(user, name, currency, financialInstitution);
    }

    public SavingsAccount(String id, CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(id, user, name, currency, financialInstitution);
    }
}
