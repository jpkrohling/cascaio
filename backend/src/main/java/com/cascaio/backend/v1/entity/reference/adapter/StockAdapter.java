/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.StockCreateRequest;
import com.cascaio.api.v1.reference.StockResponse;
import com.cascaio.api.v1.reference.StockUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.StockMarketService;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.Stock;
import com.cascaio.backend.v1.entity.reference.StockMarket;

import javax.inject.Inject;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class StockAdapter extends EntityAdapter<StockCreateRequest,StockUpdateRequest,StockResponse,Stock> {

    @Inject
    StockMarketAdapter stockMarketAdapter;

    @Inject
    StockMarketService stockMarketService;

    @Override
    public StockResponse adaptPersistent(Stock stock) {
        StockResponse response = new StockResponse();
        response.setId(stock.getId());
        response.setSymbol(stock.getSymbol());
        response.setName(stock.getName());
        response.setMarket(stockMarketAdapter.adaptPersistent(stock.getMarket()));
        return response;
    }

    @Override
    public Stock adaptUpdate(StockUpdateRequest request, Stock toUpdate) {

        if (isSet(request.getName())) {
            toUpdate.setName(request.getName());
        }

        if (isSet(request.getMarket())) {
            toUpdate.setMarket(stockMarketService.readAsEntity(request.getMarket()));
        }

        return toUpdate;
    }

    @Override
    public Stock adaptCreate(StockCreateRequest request) {
        StockMarket stockMarket = stockMarketService.readAsEntity(request.getMarketId());
        return new Stock(request.getSymbol(), request.getName(), stockMarket);
    }
}
