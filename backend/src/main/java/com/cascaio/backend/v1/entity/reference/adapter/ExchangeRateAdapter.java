package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.ExchangeRateCreateRequest;
import com.cascaio.api.v1.reference.ExchangeRateResponse;
import com.cascaio.api.v1.reference.ExchangeRateUpdateRequest;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.ExchangeRate;
import org.joda.money.CurrencyUnit;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ExchangeRateAdapter extends
        EntityAdapter<ExchangeRateCreateRequest, ExchangeRateUpdateRequest, ExchangeRateResponse, ExchangeRate> {
    
    @Inject
    CurrencyAdapter currencyAdapter;
    
    @Inject
    DateTimeAdapter dateTimeAdapter;

    @Override
    public ExchangeRateResponse adaptPersistent(ExchangeRate exchangeRate) {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setId(exchangeRate.getId());
        response.setCurrencyFrom(currencyAdapter.adapt(exchangeRate.getCurrencyFrom()));
        response.setCurrencyTo(currencyAdapter.adapt(exchangeRate.getCurrencyTo()));
        response.setDate(dateTimeAdapter.adapt(exchangeRate.getDate()));
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
        CurrencyUnit from = currencyAdapter.adapt(request.getCurrencyFrom());
        CurrencyUnit to = currencyAdapter.adapt(request.getCurrencyTo());
        BigDecimal rate = new BigDecimal(request.getRate());
        LocalDate date = dateTimeAdapter.adaptToLocalDate(request.getDate());
        return new ExchangeRate(from, to, rate, date);
    }
}
