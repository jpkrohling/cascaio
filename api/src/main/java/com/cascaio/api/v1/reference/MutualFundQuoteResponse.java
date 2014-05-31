package com.cascaio.api.v1.reference;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class MutualFundQuoteResponse {
    private String id;
    private MutualFundResponse mutualFund;
    private String date;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MutualFundResponse getMutualFund() {
        return mutualFund;
    }

    public void setMutualFund(MutualFundResponse mutualFund) {
        this.mutualFund = mutualFund;
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
