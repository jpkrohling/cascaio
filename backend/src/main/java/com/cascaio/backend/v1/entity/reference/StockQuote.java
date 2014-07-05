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

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"stock_id", "date"})})
public class StockQuote extends Quote {

    @ManyToOne
    private Stock stock;

    protected StockQuote() {
    }

    public StockQuote(LocalDate date, BigDecimal price, Stock stock) {
        super(date, price);
        this.stock = stock;
    }

    public StockQuote(String id, LocalDate date, BigDecimal price, Stock stock) {
        super(id, date, price);
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockQuote)) return false;
        if (!super.equals(o)) return false;

        StockQuote that = (StockQuote) o;

        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockQuote{" +
                "stock=" + stock +
                ",quote=" + super.toString() +
                '}';
    }
}
