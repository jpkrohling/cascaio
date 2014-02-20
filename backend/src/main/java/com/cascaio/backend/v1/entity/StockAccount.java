package com.cascaio.backend.v1.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * User: jpkrohling
 * Date: 6/30/13
 * Time: 7:53 PM
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
