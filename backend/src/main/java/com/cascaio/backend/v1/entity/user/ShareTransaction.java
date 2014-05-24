package com.cascaio.backend.v1.entity.user;

import org.joda.time.LocalDate;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
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
