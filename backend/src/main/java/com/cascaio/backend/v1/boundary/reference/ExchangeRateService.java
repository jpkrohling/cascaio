package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.ExchangeRateCreateRequest;
import com.cascaio.api.v1.reference.ExchangeRateResponse;
import com.cascaio.api.v1.reference.ExchangeRateUpdateRequest;
import com.cascaio.api.v1.validation.Currency;
import com.cascaio.api.v1.validation.ISODate;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.ExchangeRate;
import com.cascaio.backend.v1.entity.reference.ExchangeRate_;
import com.cascaio.backend.v1.entity.reference.MutualFundQuote;
import com.cascaio.backend.v1.entity.reference.MutualFundQuote_;
import com.cascaio.backend.v1.entity.reference.adapter.DateTimeAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.ExchangeRateAdapter;
import org.joda.money.CurrencyUnit;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/exchangeRates")
@Stateless
public class ExchangeRateService extends BaseService<
        ExchangeRateCreateRequest,
        ExchangeRateUpdateRequest,
        ExchangeRateResponse,
        ExchangeRate,
        ExchangeRateAdapter> {

    @QueryParam("currencyFrom")
    @Currency
    @NotNull
    @Size(min=3, max=3)
    private String currencyFrom;

    @QueryParam("currencyTo")
    @Currency
    @NotNull
    @Size(min=3, max=3)
    private String currencyTo;

    @QueryParam("dateStart")
    @ISODate
    @NotNull
    private String dateStart;

    @QueryParam("dateEnd")
    @ISODate
    @NotNull
    private String dateEnd;

    @Inject
    private ExchangeRateAdapter adapter;

    @Inject
    private DateTimeAdapter dateTimeAdapter;

    @GET
    @Override
    @RolesAllowed({"admin", "user"})
    public List<ExchangeRateResponse> list() {
        return adapter.adaptPersistent(listAsEntity());
    }

    @Override
    @RolesAllowed({"admin", "user"})
    public List<ExchangeRate> listAsEntity() {
        CurrencyUnit from = CurrencyUnit.of(currencyFrom);
        CurrencyUnit to = CurrencyUnit.of(currencyTo);

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> query = builder.createQuery(ExchangeRate.class);
        Root<ExchangeRate> root = query.from(ExchangeRate.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<Predicate>(4);
        predicates.add(builder.equal(root.get(ExchangeRate_.currencyFrom), from));
        predicates.add(builder.equal(root.get(ExchangeRate_.currencyTo), to));

        if (null != dateStart && !dateStart.isEmpty()) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(ExchangeRate_.date), dateTimeAdapter.adaptToLocalDate(dateStart)));
        }

        if (null != dateEnd && !dateEnd.isEmpty()) {
            predicates.add(builder.lessThanOrEqualTo(root.get(ExchangeRate_.date), dateTimeAdapter.adaptToLocalDate(dateEnd)));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return getEntityManager().createQuery(query).getResultList();
    }
}
