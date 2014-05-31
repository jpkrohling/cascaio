package com.cascaio.backend.v1.control.batch.stockquote;

import com.cascaio.backend.v1.boundary.reference.StockService;
import com.cascaio.backend.v1.entity.reference.Stock;
import org.slf4j.Logger;

import javax.batch.api.partition.PartitionPlanImpl;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class StockQuotePartitionPlan extends PartitionPlanImpl {

    @Inject
    StockService stockService;

    @Inject
    Logger logger;

    private List<Stock> stockList;
    private static final int MAX_THREADS = 250;

    @Override
    public int getPartitions() {
        // one partition per stock
        return getStockList().size();
    }

    @Override
    public int getThreads() {
        return Math.min(getStockList().size(), MAX_THREADS); // at most, MAX_THREADS threads
    }

    @Override
    public Properties[] getPartitionProperties() {
        Properties[] properties = new Properties[getPartitions()];
        for (int i = 0 ; i < getPartitions() ; i++) {
            Stock stock = getStockList().get(i);
            logger.trace("Adding to {} partition plan position {}", stock.getSymbol(), i);
            properties[i] = new Properties();
            properties[i].put("stockSymbol", stock.getSymbol());
            properties[i].put("stockId", stock.getId());
        }

        return properties;
    }

    public List<Stock> getStockList() {
        return getAllStocks();
    }

    public List<Stock> getSingleStockList() {
        List<Stock> stocks = new ArrayList<>(1);
        stocks.add(stockService.readAsEntity("b4031c4b-6e6b-4dda-8a63-68251e686f97")); // RHT at NYSE
        return stocks;
    }

    public List<Stock> getAllStocks() {
        if (null == stockList) {
            stockList = stockService.listAsEntity();
        }
        return stockList;
    }
}
