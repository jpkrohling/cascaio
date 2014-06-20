package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.BaseUpdateRequest;
import com.cascaio.api.v1.validation.NumericRate;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ExchangeRateUpdateRequest implements BaseUpdateRequest {
    @NotNull
    private String id;

    @NotNull
    @NumericRate
    private String rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
