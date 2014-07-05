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

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class SavingsAccount extends FinancialInstitutionAccount {
    public SavingsAccount() {
    }

    /**
     * Duplicates a CheckingAccount into a SavingsAccount
     * @param checkingAccount
     */
    public SavingsAccount(CheckingAccount checkingAccount) {
        super(checkingAccount.getUser(), checkingAccount.getName(), checkingAccount.getCurrency(), checkingAccount.getFinancialInstitution());
    }

    public SavingsAccount(CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(user, name, currency, financialInstitution);
    }

    public SavingsAccount(String id, CascaioUser user, String name, CurrencyUnit currency, FinancialInstitution financialInstitution) {
        super(id, user, name, currency, financialInstitution);
    }
}
