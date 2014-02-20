package com.cascaio.backend.v1.entity;

import org.joda.time.LocalDate;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * User: jpkroehling
 * Date: 2014-02-12
 * Time: 6:14 PM
 */
@MappedSuperclass
public abstract class ShareTransaction extends Transaction {
    public ShareTransaction() {
    }

    public ShareTransaction(LocalDate date, BigDecimal amount, Account account) {
        super(date, amount, account);
    }

    public ShareTransaction(String name, LocalDate date, BigDecimal amount, Account account) {
        super(name, date, amount, account);
    }

    public ShareTransaction(String id, String name, LocalDate date, BigDecimal amount, Account account) {
        super(id, name, date, amount, account);
    }
}
