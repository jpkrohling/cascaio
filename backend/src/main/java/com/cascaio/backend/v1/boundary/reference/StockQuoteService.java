package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.api.v1.reference.StockQuoteCreateRequest;
import com.cascaio.api.v1.reference.StockQuoteQueryRequest;
import com.cascaio.api.v1.reference.StockQuoteResponse;
import com.cascaio.api.v1.reference.StockQuoteUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.Stock;
import com.cascaio.backend.v1.entity.reference.StockQuote;
import com.cascaio.backend.v1.entity.reference.StockQuote_;
import com.cascaio.backend.v1.entity.reference.adapter.DateTimeAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.StockQuoteAdapter;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Path;
import org.joda.time.LocalDate;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/stocks/{stockId}/quotes")
@Stateless
public class StockQuoteService extends BaseService<
        StockQuoteCreateRequest,
        StockQuoteUpdateRequest,
        StockQuoteQueryRequest,
        ReadRequestById,
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

    @Override
    @RolesAllowed("admin")
    public List<StockQuote> listAsEntity(StockQuoteQueryRequest request) {
        Stock stock = stockService.readAsEntity(request.getStockId());
        return getByStockAsEntity(stock);
    }


    @RolesAllowed("admin")
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

        if (stockQuoteList.isEmpty()) {
            return null;
        }

        return stockQuoteList.get(0);
    }

    @RolesAllowed("admin")
    public List<StockQuote> getByStockAsEntity(Stock stock) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StockQuote> query = builder.createQuery(StockQuote.class);
        Root<StockQuote> root = query.from(StockQuote.class);
        query.select(root);
        query.where(
                builder.equal(root.get(StockQuote_.stock), stock)
        );

        return getEntityManager().createQuery(query).getResultList();
    }
}
