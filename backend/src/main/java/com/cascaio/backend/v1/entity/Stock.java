package com.cascaio.backend.v1.entity;

import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class Stock extends CascaioEntity {

    @NotNull
    private String symbol;

    @NotNull
    @ManyToOne
    private StockMarket market;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockQuote> quotes = new ArrayList<>();

    // JPA happy
    protected Stock() {
    }

    public Stock(String symbol, String name, StockMarket market) {
        this.symbol = symbol;
        this.name = name;
        this.market = market;
    }

    public Stock(String id, String symbol, String name, StockMarket market) {
        super(id);
        this.symbol = symbol;
        this.market = market;
        this.name = name;
    }

    public StockMarket getMarket() {
        return market;
    }

    public void setMarket(StockMarket market) {
        this.market = market;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<StockQuote> getQuotes() {
        return Collections.unmodifiableList(quotes);
    }

    public StockQuote addQuote(DateTime date, BigDecimal price) {
        StockQuote quote = new StockQuote(date, price, this);
        this.quotes.add(quote);
        return quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;

        Stock stock = (Stock) o;

        if (market != null ? !market.equals(stock.market) : stock.market != null) return false;
        if (symbol != null ? !symbol.equals(stock.symbol) : stock.symbol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (market != null ? market.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", market=" + market +
                '}';
    }
}
