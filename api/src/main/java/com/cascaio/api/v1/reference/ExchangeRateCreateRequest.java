package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.validation.Currency;
import com.cascaio.api.v1.validation.ISODate;
import com.cascaio.api.v1.validation.NumericRate;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ExchangeRateCreateRequest {

    @NotNull
    @Currency
    private String currencyFrom;

    @NotNull
    @Currency
    private String currencyTo;

    @NotNull
    @ISODate
    private String date;

    @NotNull
    @NumericRate
    private String rate;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
