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

import com.cascaio.backend.v1.entity.NamedCascaioEntity;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction extends NamedCascaioEntity {

    @NotNull
    private LocalDate date;

    @NotNull
    @Column(precision = 12, scale = 6)
    private BigDecimal amount;

    @ManyToOne
    @NotNull
    private Account account;

    // JPA happy
    protected Transaction() {
    }

    public Transaction(LocalDate date, BigDecimal amount, Account account) {
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    public Transaction(String name, LocalDate date, BigDecimal amount, Account account) {
        super(name);
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    public Transaction(String id, String name, LocalDate date, BigDecimal amount, Account account) {
        super(id, name);
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }
}
