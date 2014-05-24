package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.ExchangeRateCreateRequest;
import com.cascaio.api.v1.reference.ExchangeRateResponse;
import com.cascaio.api.v1.reference.ExchangeRateUpdateRequest;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.ExchangeRate;
import org.joda.money.CurrencyUnit;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ExchangeRateAdapter extends EntityAdapter<ExchangeRateCreateRequest, ExchangeRateUpdateRequest, ExchangeRateResponse, ExchangeRate> {
    
    @Inject
    DateTimeFormatter dateTimeFormatter;
    
    @Override
    public ExchangeRateResponse adaptPersistent(ExchangeRate exchangeRate) {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setId(exchangeRate.getId());
        response.setCurrencyFrom(exchangeRate.getCurrencyFrom().getCurrencyCode());
        response.setCurrencyTo(exchangeRate.getCurrencyTo().getCurrencyCode());
        response.setDate(exchangeRate.getDate().toString(dateTimeFormatter));
        response.setRate(exchangeRate.getPrice().toString());
        return response;
    }

    @Override
    public ExchangeRate adaptUpdate(ExchangeRateUpdateRequest request, ExchangeRate toUpdate) {
        if (isSet(request.getRate())) {
            toUpdate.setPrice(new BigDecimal(request.getRate()));
        }

        return toUpdate;
    }

    @Override
    public ExchangeRate adaptCreate(ExchangeRateCreateRequest request) {
        CurrencyUnit from = CurrencyUnit.of(request.getCurrencyFrom());
        CurrencyUnit to = CurrencyUnit.of(request.getCurrencyTo());
        BigDecimal rate = new BigDecimal(request.getRate());
        DateTime date = new DateTime(request.getDate());
        return new ExchangeRate(from, to, rate, date);
    }
}
