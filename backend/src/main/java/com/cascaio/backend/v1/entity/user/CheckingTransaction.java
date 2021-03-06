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

import com.cascaio.backend.v1.entity.reference.Category;
import org.joda.money.CurrencyUnit;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CheckingTransaction extends MoneyTransaction {
    protected CheckingTransaction() {
    }

    public CheckingTransaction(LocalDate date, BigDecimal amount, Account account) {
        super(date, amount, account);
    }

    public CheckingTransaction(String name, LocalDate date, BigDecimal amount, Account account) {
        super(name, date, amount, account);
    }

    public CheckingTransaction(String id, String name, LocalDate date, BigDecimal amount, Account account) {
        super(id, name, date, amount, account);
    }

    public CheckingTransaction(LocalDate date, BigDecimal amount, Account account, String originalDescription, CurrencyUnit originalCurrency, BigDecimal originalAmount, BigDecimal exchangeRate, Category category) {
        super(date, amount, account, originalDescription, originalCurrency, originalAmount, exchangeRate, category);
    }

    public CheckingTransaction(String name, LocalDate date, BigDecimal amount, Account account, String originalDescription, CurrencyUnit originalCurrency, BigDecimal originalAmount, BigDecimal exchangeRate, Category category) {
        super(name, date, amount, account, originalDescription, originalCurrency, originalAmount, exchangeRate, category);
    }

    public CheckingTransaction(String id, String name, LocalDate date, BigDecimal amount, Account account, String originalDescription, CurrencyUnit originalCurrency, BigDecimal originalAmount, BigDecimal exchangeRate, Category category) {
        super(id, name, date, amount, account, originalDescription, originalCurrency, originalAmount, exchangeRate, category);
    }
}
