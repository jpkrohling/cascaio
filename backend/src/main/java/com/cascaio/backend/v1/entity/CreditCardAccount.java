package com.cascaio.backend.v1.entity;

import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CreditCardAccount extends MoneyAccount {
    protected CreditCardAccount() {
    }

    public CreditCardAccount(CascaioUser user, String name, CurrencyUnit currency) {
        super(user, name, currency);
    }

    public CreditCardAccount(String id, CascaioUser user, String name, CurrencyUnit currency) {
        super(id, user, name, currency);
    }
}
