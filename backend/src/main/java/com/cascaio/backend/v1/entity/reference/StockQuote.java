package com.cascaio.backend.v1.entity.reference;

import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"stock_id", "date"})})
public class StockQuote extends Quote {

    @ManyToOne
    private Stock stock;

    protected StockQuote() {
    }

    protected StockQuote(DateTime date, BigDecimal price, Stock stock) {
        super(date, price);
        this.stock = stock;
    }

    protected StockQuote(String id, DateTime date, BigDecimal price, Stock stock) {
        super(id, date, price);
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockQuote)) return false;
        if (!super.equals(o)) return false;

        StockQuote that = (StockQuote) o;

        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockQuote{" +
                "stock=" + stock +
                ",quote=" + super.toString() +
                '}';
    }
}
