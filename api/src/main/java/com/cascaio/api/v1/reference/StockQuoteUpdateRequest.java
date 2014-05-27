package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.BaseUpdateRequest;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class StockQuoteUpdateRequest implements BaseUpdateRequest {
    private String id;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
