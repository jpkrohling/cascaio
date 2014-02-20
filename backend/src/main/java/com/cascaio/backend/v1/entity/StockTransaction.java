package com.cascaio.backend.v1.entity;

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * User: jpkroehling
 * Date: 2014-02-12
 * Time: 6:14 PM
 */
@Entity
public class StockTransaction extends ShareTransaction {
    public StockTransaction() {
    }

    public StockTransaction(LocalDate date, BigDecimal amount, Account account) {
        super(date, amount, account);
    }

    public StockTransaction(String name, LocalDate date, BigDecimal amount, Account account) {
        super(name, date, amount, account);
    }

    public StockTransaction(String id, String name, LocalDate date, BigDecimal amount, Account account) {
        super(id, name, date, amount, account);
    }
}
