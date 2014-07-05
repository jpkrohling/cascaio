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
