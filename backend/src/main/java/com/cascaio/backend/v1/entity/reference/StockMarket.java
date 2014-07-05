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

import com.cascaio.backend.v1.entity.NamedCascaioEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class StockMarket extends NamedCascaioEntity {

    @NotNull
    @Column(unique = true)
    private String symbol;

    @OneToMany(mappedBy = "market", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Stock> stocks = new ArrayList<>();

    // JPA happy
    protected StockMarket() {
    }

    public StockMarket(String name, String symbol) {
        super(name);
        this.symbol = symbol;
    }

    public StockMarket(String id, String name, String symbol) {
        super(id, name);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "StockMarket{" +
                ",name='" + getName() + '\'' +
                "symbol='" + symbol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockMarket)) return false;
        if (!super.equals(o)) return false;

        StockMarket that = (StockMarket) o;

        if (!symbol.equals(that.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();

        if (null != symbol) result = 31 * result + symbol.hashCode();

        return result;
    }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(stocks);
    }
}
