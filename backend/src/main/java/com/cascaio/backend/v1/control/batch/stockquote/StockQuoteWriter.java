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
import com.cascaio.backend.v1.control.batch.BasicBatchCheckpoint;
import com.cascaio.backend.v1.entity.reference.StockQuote;
import org.slf4j.Logger;

import javax.batch.api.chunk.ItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class StockQuoteWriter implements ItemWriter {

    @Inject
    Logger logger;

    @Inject
    EntityManager entityManager;

    @Inject
    StockQuoteService stockQuoteService;

    private BasicBatchCheckpoint checkpoint;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object item : items) {
            StockQuote stockQuote = (StockQuote) item;
            checkpoint.incrementAndGet();
            logger.trace("Writing item", item);
            entityManager.persist(stockQuote);
        }
    }

    @Override
    public void open(Serializable serializable) throws Exception {
        if (null == serializable) {
            logger.trace("Creating new checkpoint");
            checkpoint = new BasicBatchCheckpoint();
        } else {
            logger.trace("Checkpoint exists");
            checkpoint = (BasicBatchCheckpoint) serializable;
        }
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
