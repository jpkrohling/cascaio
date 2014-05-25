package com.cascaio.backend.v1.control.batch.exchangerate;

import org.slf4j.Logger;

import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class ExchangeRatePartitionMapper implements PartitionMapper {

    @Inject
    ExchangeRatePartitionPlan partitionPlan;

    @Inject
    Logger logger;

    @Override
    public PartitionPlan mapPartitions() throws Exception {
        try {
            logger.trace("Returning partition plan");
            return partitionPlan;
        } catch (Exception e) {
            logger.error("Caught on ExchangeRatePartitionPlan", e);
            e.printStackTrace();
            throw e;
        }
    }
}
