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
