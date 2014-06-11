package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.StockQuoteCreateRequest;
import com.cascaio.api.v1.reference.StockQuoteResponse;
import com.cascaio.api.v1.reference.StockQuoteUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.Stock;
import com.cascaio.backend.v1.entity.reference.StockMarket;
import com.cascaio.backend.v1.entity.reference.StockQuote;
import com.cascaio.backend.v1.entity.reference.StockQuote_;
import com.cascaio.backend.v1.entity.reference.adapter.DateTimeAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.StockQuoteAdapter;
import org.joda.time.LocalDate;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
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
@Path("/reference/stockQuotes")
@Stateless
public class StockQuoteService extends BaseService<
        StockQuoteCreateRequest,
        StockQuoteUpdateRequest,
        StockQuoteResponse,
        StockQuote,
        StockQuoteAdapter> {

    @Inject
    Logger logger;

    @Inject
    DateTimeAdapter dateTimeAdapter;

    @Inject
    StockService stockService;

    @Inject
    StockMarketService stockMarketService;

    @Path("/{marketSymbol}:{symbol}/{isoDate}")
    @GET
    public StockQuoteResponse getBySymbolAndDate(@PathParam("symbol") String symbol,
                                                 @PathParam("marketSymbol") String marketSymbol,
                                                 @PathParam("isoDate") String isoDate) {
        LocalDate date = dateTimeAdapter.adaptToLocalDate(isoDate);
        StockMarket stockMarket = stockMarketService.getBySymbolAsEntity(marketSymbol);
        Stock stock = stockService.getBySymbolAsEntity(symbol, stockMarket);
        return getAdapter().adaptPersistent(getByStockAndDateAsEntity(stock, date));
    }

    public StockQuote getByStockAndDateAsEntity(Stock stock, LocalDate date) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StockQuote> query = builder.createQuery(StockQuote.class);
        Root<StockQuote> root = query.from(StockQuote.class);
        query.select(root);
        query.where(
                builder.equal(root.get(StockQuote_.stock), stock),
                builder.equal(root.get(StockQuote_.date), date)
        );

        List<StockQuote> stockQuoteList = getEntityManager().createQuery(query).getResultList();

        if (stockQuoteList.size() > 1) {
            String message = "More than one quote found for the stock "+stock+" on market "+stock.getMarket()+" on date " + date;
            logger.warn(message);
            throw new NonUniqueResultException(message);
        }

        if (stockQuoteList.size() == 0) {
            return null;
        }

        return stockQuoteList.get(0);
    }
}
