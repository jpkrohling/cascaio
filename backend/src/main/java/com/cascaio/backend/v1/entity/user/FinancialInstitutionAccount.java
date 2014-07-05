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

import com.cascaio.backend.v1.entity.reference.FinancialInstitution;
import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FinancialInstitutionAccount extends MoneyAccount {
    @ManyToOne
    @NotNull
    private FinancialInstitution financialInstitution;

    // JPA happy
    protected FinancialInstitutionAccount() {
    }

    public FinancialInstitutionAccount(CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(user, name, currency);
        this.financialInstitution = financialInstitution;
    }

    public FinancialInstitutionAccount(String id, CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(id, user, name, currency);
        this.financialInstitution = financialInstitution;
    }

    public void setFinancialInstitution(FinancialInstitution financialInstitution) {
        this.financialInstitution = financialInstitution;
    }

    public FinancialInstitution getFinancialInstitution() {
        return financialInstitution;
    }

}
