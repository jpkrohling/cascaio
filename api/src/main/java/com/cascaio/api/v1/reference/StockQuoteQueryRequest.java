package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.BaseQueryRequest;
import javax.ws.rs.PathParam;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class StockQuoteQueryRequest extends BaseQueryRequest {

    @PathParam("stockId")
    private String stockId;

    public StockQuoteQueryRequest(String stockId) {
        this.stockId = stockId;
    }

    public StockQuoteQueryRequest() {
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

}
