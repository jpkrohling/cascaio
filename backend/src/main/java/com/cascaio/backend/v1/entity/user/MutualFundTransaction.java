package com.cascaio.backend.v1.entity.user;

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
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
