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
import org.joda.time.LocalDate;

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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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

    @Inject
    private ExchangeRateAdapter adapter;

    @Inject
    private DateTimeAdapter dateTimeAdapter;

    @Override
    @RolesAllowed({"user", "admin"})
    public List<ExchangeRateResponse> list() {
        CurrencyUnit from = CurrencyUnit.of(getServletRequest().getParameter("currencyFrom"));
        CurrencyUnit to = CurrencyUnit.of(getServletRequest().getParameter("currencyTo"));
        String dateStart = getServletRequest().getParameter("dateStart");
        String dateEnd = getServletRequest().getParameter("dateEnd");

        LocalDate localDateStart = null;
        LocalDate localDateEnd = null;

        if (null != dateStart && !dateStart.isEmpty()) {
            localDateStart = dateTimeAdapter.adaptToLocalDate(dateStart);
        }

        if (null != dateEnd && !dateEnd.isEmpty()) {
            localDateEnd = dateTimeAdapter.adaptToLocalDate(dateEnd);
        }

        return adapter.adaptPersistent(listAsEntity(from, to, localDateStart, localDateEnd));
    }

    @Override
    public List<ExchangeRate> listAsEntity() {
        throw new RuntimeException("listAsEntity() is not allowed for Exchange Rate");
    }

    @RolesAllowed({"admin", "user"})
    public List<ExchangeRate> listAsEntity(CurrencyUnit from, CurrencyUnit to, LocalDate dateStart, LocalDate dateEnd) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> query = builder.createQuery(ExchangeRate.class);
        Root<ExchangeRate> root = query.from(ExchangeRate.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<Predicate>(4);
        predicates.add(builder.equal(root.get(ExchangeRate_.currencyFrom), from));
        predicates.add(builder.equal(root.get(ExchangeRate_.currencyTo), to));

        if (null != dateStart) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(ExchangeRate_.date), dateStart));
        }

        if (null != dateEnd) {
            predicates.add(builder.lessThanOrEqualTo(root.get(ExchangeRate_.date), dateEnd));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return getEntityManager().createQuery(query).getResultList();
    }
}
