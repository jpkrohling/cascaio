package com.cascaio.backend.v1.entity;

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * User: jpkroehling
 * Date: 2014-02-12
 * Time: 6:13 PM
 */
@Entity
public class MutualFundTransaction extends ShareTransaction {
    public MutualFundTransaction() {
    }

    public MutualFundTransaction(LocalDate date, BigDecimal amount, Account account) {
        super(date, amount, account);
    }

    public MutualFundTransaction(String name, LocalDate date, BigDecimal amount, Account account) {
        super(name, date, amount, account);
    }

    public MutualFundTransaction(String id, String name, LocalDate date, BigDecimal amount, Account account) {
        super(id, name, date, amount, account);
    }
}
