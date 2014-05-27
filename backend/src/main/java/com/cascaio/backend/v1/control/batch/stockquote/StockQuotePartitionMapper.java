package com.cascaio.backend.v1.control.batch.stockquote;

import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class StockQuotePartitionMapper implements PartitionMapper {

    @Inject
    StockQuotePartitionPlan partitionPlan;

    @Override
    public PartitionPlan mapPartitions() throws Exception {
        return partitionPlan;
    }
}
