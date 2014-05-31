package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.MutualFundQuoteCreateRequest;
import com.cascaio.api.v1.reference.MutualFundQuoteResponse;
import com.cascaio.api.v1.reference.MutualFundQuoteUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.MutualFund;
import com.cascaio.backend.v1.entity.reference.MutualFundQuote;
import com.cascaio.backend.v1.entity.reference.MutualFundQuote_;
import com.cascaio.backend.v1.entity.reference.adapter.DateTimeAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.MutualFundQuoteAdapter;
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
@Path("/reference/mutualFundQuotes")
@Stateless
public class MutualFundQuoteService extends BaseService<
        MutualFundQuoteCreateRequest,
        MutualFundQuoteUpdateRequest,
        MutualFundQuoteResponse,
        MutualFundQuote,
        MutualFundQuoteAdapter> {

    @Inject
    Logger logger;

    @Inject
    MutualFundQuoteAdapter adapter;

    @Inject
    DateTimeAdapter dateTimeAdapter;

    @Inject
    MutualFundService mutualFundService;

    @Path("/isin:{isin}/{isoDate}")
    @GET
    public MutualFundQuoteResponse getByIsinAndDate(@PathParam("isin") String isin, @PathParam("isoDate") String isoDate) {
        LocalDate date = dateTimeAdapter.adaptToLocalDate(isoDate);
        MutualFund mutualFund = mutualFundService.getByIsinAsEntity(isin);
        return adapter.adaptPersistent(getByMutualFundAndDateAsEntity(mutualFund, date));
    }

    public MutualFundQuote getByMutualFundAndDateAsEntity(MutualFund mutualFund, LocalDate date) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MutualFundQuote> query = builder.createQuery(MutualFundQuote.class);
        Root<MutualFundQuote> root = query.from(MutualFundQuote.class);
        query.select(root);
        query.where(
                builder.equal(root.get(MutualFundQuote_.mutualFund), mutualFund),
                builder.equal(root.get(MutualFundQuote_.date), date)
        );

        List<MutualFundQuote> mutualFundQuoteList = getEntityManager().createQuery(query).getResultList();

        if (mutualFundQuoteList.size() > 1) {
            String message = "More than one quote found for the mutual fund " + mutualFund.getIsin() + " for " + date;
            logger.warn(message);
            throw new NonUniqueResultException(message);
        }

        if (mutualFundQuoteList.size() == 0) {
            return null;
        }

        return mutualFundQuoteList.get(0);
    }

    @Override
    public MutualFundQuoteAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<MutualFundQuote> getPersistentClass() {
        return MutualFundQuote.class;
    }
}
