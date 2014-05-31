package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.StockQuoteCreateRequest;
import com.cascaio.api.v1.reference.StockQuoteResponse;
import com.cascaio.api.v1.reference.StockQuoteUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.StockService;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.Stock;
import com.cascaio.backend.v1.entity.reference.StockQuote;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class StockQuoteAdapter extends
        EntityAdapter<StockQuoteCreateRequest, StockQuoteUpdateRequest, StockQuoteResponse, StockQuote> {

    @Inject
    DateTimeAdapter dateTimeAdapter;

    @Inject
    BigDecimalAdapter bigDecimalAdapter;

    @Inject
    StockAdapter stockAdapter;

    @Inject
    StockService stockService;

    @Override
    public StockQuoteResponse adaptPersistent(StockQuote stockQuote) {
        StockQuoteResponse response = new StockQuoteResponse();
        response.setId(stockQuote.getId());
        response.setDate(dateTimeAdapter.adapt(stockQuote.getDate()));
        response.setPrice(bigDecimalAdapter.adapt(stockQuote.getPrice()));
        response.setStock(stockAdapter.adaptPersistent(stockQuote.getStock()));
        return response;
    }

    @Override
    public StockQuote adaptUpdate(StockQuoteUpdateRequest request, StockQuote toUpdate) {
        if (isSet(request.getPrice())) {
            toUpdate.setPrice(bigDecimalAdapter.adapt(request.getPrice()));
        }
        return toUpdate;
    }

    @Override
    public StockQuote adaptCreate(StockQuoteCreateRequest request) {
        LocalDate date = dateTimeAdapter.adaptToLocalDate(request.getDate());
        BigDecimal price = bigDecimalAdapter.adapt(request.getPrice());
        Stock stock = stockService.readAsEntity(request.getStockId());
        return new StockQuote(date, price, stock);
    }
}
