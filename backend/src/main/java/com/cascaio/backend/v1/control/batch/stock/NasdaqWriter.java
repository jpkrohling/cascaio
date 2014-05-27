package com.cascaio.backend.v1.control.batch.stock;

import com.cascaio.backend.v1.control.batch.BasicBatchCheckpoint;
import com.cascaio.backend.v1.entity.reference.Stock;
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
public class NasdaqWriter implements ItemWriter {

    @Inject
    EntityManager entityManager;

    @Inject
    Logger logger;

    private BasicBatchCheckpoint checkpoint;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object item : items) {
            checkpoint.incrementAndGet();
            logger.trace("Writing item", item);
            Stock stock = (Stock) item;
            entityManager.persist(stock);
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
