package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.validation.ISODate;
import com.cascaio.api.v1.validation.NumericRate;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class MutualFundQuoteCreateRequest {

    @NotNull
    private String mutualFundId;

    @NotNull
    @ISODate
    private String date;

    @NotNull
    @NumericRate
    private String price;

    public String getMutualFundId() {
        return mutualFundId;
    }

    public void setMutualFundId(String mutualFundId) {
        this.mutualFundId = mutualFundId;
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
