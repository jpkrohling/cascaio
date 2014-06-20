package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.BaseResponse;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class StockQuoteResponse extends BaseResponse {
    private String id;
    private StockResponse stock;
    private String date;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StockResponse getStock() {
        return stock;
    }

    public void setStock(StockResponse stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
