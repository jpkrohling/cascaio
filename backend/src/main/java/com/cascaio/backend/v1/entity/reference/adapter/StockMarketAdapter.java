package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.StockMarketCreateRequest;
import com.cascaio.api.v1.reference.StockMarketResponse;
import com.cascaio.api.v1.reference.StockMarketUpdateRequest;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.StockMarket;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class StockMarketAdapter extends
        EntityAdapter<StockMarketCreateRequest, StockMarketUpdateRequest, StockMarketResponse, StockMarket> {

    @Override
    public StockMarketResponse adaptPersistent(StockMarket stockMarket) {
        StockMarketResponse response = new StockMarketResponse();
        response.setId(stockMarket.getId());
        response.setName(stockMarket.getName());
        response.setSymbol(stockMarket.getSymbol());
        return response;
    }

    @Override
    public StockMarket adaptUpdate(StockMarketUpdateRequest request, StockMarket toUpdate) {
        if (isSet(request.getSymbol())) {
            toUpdate.setSymbol(request.getSymbol());
        }
        return toUpdate;
    }

    @Override
    public StockMarket adaptCreate(StockMarketCreateRequest request) {
        return new StockMarket(request.getName(), request.getSymbol());
    }
}
