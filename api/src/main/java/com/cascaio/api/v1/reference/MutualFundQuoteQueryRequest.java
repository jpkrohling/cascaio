package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.BaseQueryRequest;

import javax.ws.rs.PathParam;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class MutualFundQuoteQueryRequest extends BaseQueryRequest {

    @PathParam("fundId")
    private String fundId;

    public MutualFundQuoteQueryRequest() {
    }

    public MutualFundQuoteQueryRequest(String fundId) {
        this.fundId = fundId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }
}
