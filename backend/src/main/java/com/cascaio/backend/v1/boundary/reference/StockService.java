package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.StockCreateRequest;
import com.cascaio.api.v1.reference.StockResponse;
import com.cascaio.api.v1.reference.StockUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.Stock;
import com.cascaio.backend.v1.entity.reference.StockMarket;
import com.cascaio.backend.v1.entity.reference.Stock_;
import com.cascaio.backend.v1.entity.reference.adapter.StockAdapter;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/stocks")
@Stateless
public class StockService extends BaseService<
        StockCreateRequest,
        StockUpdateRequest,
        StockResponse,
        Stock,
        StockAdapter> {

    @Inject
    StockAdapter adapter;

    @Inject
    StockMarketService stockMarketService;

    @Inject
    Logger logger;

    @Path("/symbol/{marketSymbol}:{symbol}")
    @GET
    public StockResponse getBySymbol(@PathParam("symbol") String symbol, @PathParam("marketSymbol") String marketSymbol) {
        StockMarket stockMarket = stockMarketService.getBySymbolAsEntity(marketSymbol);
        return adapter.adaptPersistent(getBySymbolAsEntity(symbol, stockMarket));
    }

    public Stock getBySymbolAsEntity(String symbol, StockMarket stockMarket) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
        Root<Stock> root = query.from(Stock.class);
        query.select(root);
        query.where(
                builder.equal(root.get(Stock_.symbol), symbol),
                builder.equal(root.get(Stock_.market), stockMarket)
        );

        List<Stock> stockList = getEntityManager().createQuery(query).getResultList();

        if (stockList.size() > 1) {
            String message = "More than one stock symbol found on market " + stockMarket.getSymbol() + " with symbol " + symbol;
            logger.warn(message);
            throw new IllegalStateException(message);
        }

        if (stockList.size() == 0) {
            return null;
        }

        return stockList.get(0);
    }

    @Override
    public StockAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<Stock> getPersistentClass() {
        return Stock.class;
    }
}
