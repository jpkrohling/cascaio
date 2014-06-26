package com.cascaio.api.v1.reference;

import javax.ws.rs.QueryParam;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ExchangeRateQueryRequest {

    @QueryParam("currencyFrom")
    private String currencyFrom;

    @QueryParam("currencyTo")
    private String currencyTo;

    @QueryParam("dateStart")
    private String dateStart;

    @QueryParam("dateEnd")
    private String dateEnd;

    public ExchangeRateQueryRequest(String currencyFrom, String currencyTo, String dateStart, String dateEnd) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public ExchangeRateQueryRequest() {
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
