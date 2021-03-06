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
import org.joda.money.CurrencyUnit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class MutualFund extends NamedCascaioEntity {

    @NotNull
    @Column(unique = true, length = 12)
    private String isin;

    @Column(length = 6)
    private String wkn;

    @NotNull
    @Column(length = 3)
    private CurrencyUnit currency;

    @OneToMany(mappedBy = "mutualFund", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<MutualFundQuote> quotes = new ArrayList<>();

    protected MutualFund() {
    }

    public MutualFund(String name, String isin, CurrencyUnit currency) {
        this(UUID.randomUUID().toString(), name, isin, currency);
    }

    public MutualFund(String id, String name, String isin, CurrencyUnit currency) {
        super(id, name);
        this.isin = isin;
        this.currency = currency;
    }

    public List<MutualFundQuote> getQuotes() {
        return Collections.unmodifiableList(quotes);
    }

    public String getIsin() {
        return isin;
    }

    public String getWkn() {
        return wkn;
    }

    public void setWkn(String wkn) {
        this.wkn = wkn;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyUnit currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MutualFund other = (MutualFund) obj;
        if (!Objects.equals(this.isin, other.isin)) {
            return false;
        }
        if (!Objects.equals(this.wkn, other.wkn)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        return Objects.equals(this.quotes, other.quotes);
    }

    @Override
    public int hashCode() {
        int result = isin != null ? isin.hashCode() : 0;
        result = 31 * result + (wkn != null ? wkn.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (quotes != null ? quotes.hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MutualFund{" +
                "isin='" + isin + '\'' +
                ", wkn='" + wkn + '\'' +
                ", currency=" + currency +
                ", named=" + super.toString() +
                '}';
    }
}
