/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cascaio.backend.v1.entity.user;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;
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
