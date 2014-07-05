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
package com.cascaio.backend.v1.control.batch.exchangerate;

import com.cascaio.backend.v1.boundary.reference.ExchangeRateService;
import com.cascaio.backend.v1.control.batch.BasicBatchCheckpoint;
import com.cascaio.backend.v1.entity.reference.ExchangeRate;
import org.slf4j.Logger;

import javax.batch.api.chunk.ItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class ExchangeRateWriter implements ItemWriter {

    @Inject
    ExchangeRateService service;

    @Inject
    Logger logger;

    private BasicBatchCheckpoint checkpoint;

    @Override
    public void writeItems(List<Object> objects) throws Exception {
        logger.trace("Got {} items to write", objects.size());
        for (Object item : objects) {
            ExchangeRate exchangeRate = (ExchangeRate) item;
            logger.trace("Writing item", item);
            service.createAsEntity(exchangeRate);
            checkpoint.incrementAndGet();
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
