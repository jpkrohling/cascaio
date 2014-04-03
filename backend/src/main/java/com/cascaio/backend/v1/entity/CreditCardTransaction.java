package com.cascaio.backend.v1.entity;

import org.joda.money.CurrencyUnit;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CreditCardTransaction extends MoneyTransaction {
    public CreditCardTransaction() {
    }

    public CreditCardTransaction(LocalDate date, BigDecimal amount, Account account) {
        super(date, amount, account);
    }

    public CreditCardTransaction(String name, LocalDate date, BigDecimal amount, Account account) {
        super(name, date, amount, account);
    }

    public CreditCardTransaction(String id, String name, LocalDate date, BigDecimal amount, Account account) {
        super(id, name, date, amount, account);
    }

    public CreditCardTransaction(LocalDate date, BigDecimal amount, Account account, String originalDescription, CurrencyUnit originalCurrency, BigDecimal originalAmount, BigDecimal exchangeRate, Category category) {
        super(date, amount, account, originalDescription, originalCurrency, originalAmount, exchangeRate, category);
    }

    public CreditCardTransaction(String name, LocalDate date, BigDecimal amount, Account account, String originalDescription, CurrencyUnit originalCurrency, BigDecimal originalAmount, BigDecimal exchangeRate, Category category) {
        super(name, date, amount, account, originalDescription, originalCurrency, originalAmount, exchangeRate, category);
    }

    public CreditCardTransaction(String id, String name, LocalDate date, BigDecimal amount, Account account, String originalDescription, CurrencyUnit originalCurrency, BigDecimal originalAmount, BigDecimal exchangeRate, Category category) {
        super(id, name, date, amount, account, originalDescription, originalCurrency, originalAmount, exchangeRate, category);
    }
}
