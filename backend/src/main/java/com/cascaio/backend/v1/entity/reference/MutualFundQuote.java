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
import java.util.Objects;
import java.util.UUID;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"mutualFund_id", "date"})})
public class MutualFundQuote extends Quote {

    @ManyToOne
    private MutualFund mutualFund;

    // JPA happy
    protected MutualFundQuote() {
    }

    public MutualFundQuote(LocalDate date, BigDecimal price, MutualFund mutualFund) {
        this(UUID.randomUUID().toString(), date, price, mutualFund);
    }

    public MutualFundQuote(String id, LocalDate date, BigDecimal price, MutualFund mutualFund) {
        super(id, date, price);
        this.mutualFund = mutualFund;
    }

    public MutualFund getMutualFund() {
        return mutualFund;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MutualFundQuote other = (MutualFundQuote) obj;
        return Objects.equals(this.mutualFund, other.mutualFund);
    }

    @Override
    public int hashCode() {
        int result = getMutualFund().hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MutualFundQuote{" +
                "mutualFund=" + mutualFund +
                "quote=" + super.toString() +
                '}';
    }
}
