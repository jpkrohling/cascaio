package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.StockMarketCreateRequest;
import com.cascaio.api.v1.reference.StockMarketResponse;
import com.cascaio.api.v1.reference.StockMarketUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.StockMarket;
import com.cascaio.backend.v1.entity.reference.StockMarket_;
import com.cascaio.backend.v1.entity.reference.adapter.StockMarketAdapter;
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
@Path("/reference/stockMarkets")
@Stateless
public class StockMarketService extends BaseService<
        StockMarketCreateRequest,
        StockMarketUpdateRequest,
        StockMarketResponse,
        StockMarket,
        StockMarketAdapter> {

    @Inject
    Logger logger;

    @Path("/symbol/{symbol}")
    @GET
    public StockMarketResponse getBySymbol(@PathParam("symbol") String symbol) {
        return getAdapter().adaptPersistent(getBySymbolAsEntity(symbol));
    }

    public StockMarket getBySymbolAsEntity(String symbol) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StockMarket> query = builder.createQuery(StockMarket.class);
        Root<StockMarket> root = query.from(StockMarket.class);
        query.select(root);
        query.where(builder.equal(root.get(StockMarket_.symbol), symbol));

        List<StockMarket> stockMarketList = getEntityManager().createQuery(query).getResultList();

        if (stockMarketList.size() > 1) {
            String message = "More than one market found with the symbol " + symbol;
            logger.warn(message);
            throw new IllegalStateException(message);
        }

        if (stockMarketList.size() == 0) {
            return null;
        }

        return stockMarketList.get(0);
    }
}
