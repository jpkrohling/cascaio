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
package com.cascaio.backend.v1.entity.reference;

import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"currencyFrom", "currencyTo", "date"})})
public class ExchangeRate extends Quote {

    @NotNull
    private CurrencyUnit currencyFrom;

    @NotNull
    private CurrencyUnit currencyTo;

    public ExchangeRate(CurrencyUnit currencyFrom, CurrencyUnit currencyTo, BigDecimal rate, LocalDate date) {
        super(date, rate);
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    // JPA Happy
    protected ExchangeRate() {
    }

    public CurrencyUnit getCurrencyFrom() {
        return currencyFrom;
    }

    public CurrencyUnit getCurrencyTo() {
        return currencyTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeRate)) return false;
        if (!super.equals(o)) return false;

        ExchangeRate that = (ExchangeRate) o;

        if (currencyFrom != null ? !currencyFrom.equals(that.currencyFrom) : that.currencyFrom != null) return false;
        if (currencyTo != null ? !currencyTo.equals(that.currencyTo) : that.currencyTo != null) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (currencyFrom != null ? currencyFrom.hashCode() : 0);
        result = 31 * result + (currencyTo != null ? currencyTo.hashCode() : 0);
        result = 31 * super.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "currencyFrom=" + currencyFrom +
                ", currencyTo=" + currencyTo +
                ", rate=" + this.getPrice() +
                ", date=" + this.getDate() +
                '}';
    }
}
