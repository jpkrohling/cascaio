package com.cascaio.backend.v1.entity.user;

import org.joda.time.LocalDate;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
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
