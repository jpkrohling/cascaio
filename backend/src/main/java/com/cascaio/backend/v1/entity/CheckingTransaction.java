package com.cascaio.backend.v1.entity;

import org.joda.money.CurrencyUnit;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CheckingTransaction extends MoneyTransaction {
    public CheckingTransaction() {
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
