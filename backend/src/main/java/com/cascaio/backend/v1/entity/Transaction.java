package com.cascaio.backend.v1.entity;

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
