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
package com.cascaio.backend.v1.control.batch.stockquote;

import com.cascaio.backend.v1.boundary.reference.StockQuoteService;
import com.cascaio.backend.v1.boundary.reference.StockService;
import com.cascaio.backend.v1.entity.reference.Stock;
import com.cascaio.backend.v1.entity.reference.StockQuote;
import com.cascaio.backend.v1.entity.reference.adapter.BigDecimalAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.LocalDateAdapter;
import org.slf4j.Logger;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class StockQuoteProcessor implements ItemProcessor {

    @Inject
    Logger logger;

    @Inject
    LocalDateAdapter dateTimeAdapter;

    @Inject
    BigDecimalAdapter bigDecimalAdapter;

    @Inject
    @BatchProperty(name = "stockId")
    String stockId;

    @Inject
    StockService stockService;

    @Inject
    StockQuoteService stockQuoteService;

    @Override
    public Object processItem(Object item) throws Exception {
        String line = (String) item;
        logger.trace("Processing this line: {}", line);
        String[] parts = line.split("\\,");

        if (parts.length < 5) {
            logger.info("Line doesn't seems to be a quote: {}", line);
            return null;
        }

        logger.trace("Identified this field as date: {}", parts[0]);
        logger.trace("Identified this field as price: {}", parts[4]);
        LocalDate date = dateTimeAdapter.adapt(parts[0]);
        BigDecimal price = bigDecimalAdapter.adapt(parts[4]);
        Stock stock = stockService.readAsEntity(stockId);

        if (null != stockQuoteService.getByStockAndDateAsEntity(stock, date)) {
            return null; // already exists, skip
        }

        return new StockQuote(date, price, stock);
    }
}
