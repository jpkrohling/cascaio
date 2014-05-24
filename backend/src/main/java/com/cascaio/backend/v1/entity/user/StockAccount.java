package com.cascaio.backend.v1.entity.user;

import com.cascaio.backend.v1.entity.reference.Stock;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class StockAccount extends ShareAccount {

    @ManyToOne
    @NotNull
    private Stock stock;

    protected StockAccount() {
    }

    public StockAccount(CascaioUser user, String name, Stock stock) {
        super(user, name);
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }
}
