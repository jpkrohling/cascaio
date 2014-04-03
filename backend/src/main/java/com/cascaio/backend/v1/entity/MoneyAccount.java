package com.cascaio.backend.v1.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public abstract class MoneyAccount extends Account {

    @Type(type = "CurrencyUnit")
    @NotNull
    private CurrencyUnit currency;

    @Formula("( select sum(mts.amount) from Transaction mts where mts.account_id = id )")
    private BigDecimal total;

    // JPA happy
    protected MoneyAccount() {
        super();
    }

    protected MoneyAccount(CascaioUser user, String name, CurrencyUnit currency) {
        super(user, name);
        this.currency = currency;
    }

    protected MoneyAccount(String id, CascaioUser user, String name, CurrencyUnit currency) {
        super(id, user, name);
        this.currency = currency;
    }

    public void setCurrency(CurrencyUnit currency) {
        this.currency = currency;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }
}
