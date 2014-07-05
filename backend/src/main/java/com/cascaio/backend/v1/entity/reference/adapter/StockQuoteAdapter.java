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
