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
    public void writeItems(List<Object> objects) throws Exception {
        try {
            logger.trace("Got {} items to write", objects.size());
            for (Object item : objects) {
                ExchangeRate exchangeRate = (ExchangeRate) item;
                logger.trace("Writing item", item);
                try {
                    service.createAsEntity(exchangeRate);
                } catch (Exception e) {
                    logger.error("Failed to persist {}", e);
                    throw e;
                }
                checkpoint.incrementAndGet();
            }
        } catch (Exception e) {
            logger.error("Caught on ExchangeRateWriter", e);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
