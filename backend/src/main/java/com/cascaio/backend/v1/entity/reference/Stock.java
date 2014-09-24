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

import com.cascaio.backend.v1.entity.CascaioEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"market_id", "symbol"})})
public class Stock extends CascaioEntity {

    @NotNull
    private String symbol;

    @NotNull
    @ManyToOne
    private StockMarket market;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockQuote> quotes = new ArrayList<>();

    // JPA happy
    protected Stock() {
    }

    public Stock(String symbol, String name, StockMarket market) {
        this.symbol = symbol;
        this.name = name;
        this.market = market;
    }

    public Stock(String id, String symbol, String name, StockMarket market) {
        super(id);
        this.symbol = symbol;
        this.market = market;
        this.name = name;
    }

    public StockMarket getMarket() {
        return market;
    }

    public void setMarket(StockMarket market) {
        this.market = market;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<StockQuote> getQuotes() {
        return Collections.unmodifiableList(quotes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;

        Stock stock = (Stock) o;

        if (market != null ? !market.equals(stock.market) : stock.market != null) return false;
        if (symbol != null ? !symbol.equals(stock.symbol) : stock.symbol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (market != null ? market.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", market=" + market +
                '}';
    }
}
