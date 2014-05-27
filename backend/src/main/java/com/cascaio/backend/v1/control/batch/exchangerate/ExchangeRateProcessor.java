package com.cascaio.backend.v1.control.batch.exchangerate;

import com.cascaio.backend.v1.entity.reference.ExchangeRate;
import com.cascaio.backend.v1.entity.reference.ExchangeRate_;
import org.slf4j.Logger;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class ExchangeRateProcessor implements ItemProcessor {

    @Inject
    EntityManager entityManager;

    @Inject
    Logger logger;

    @Override
    public Object processItem(Object item) throws Exception {
        ExchangeRate exchangeRate = (ExchangeRate) item;
        logger.trace("Checking if we have the exchange rate for this: {}", exchangeRate);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> query = builder.createQuery(ExchangeRate.class);

        Root<ExchangeRate> root = query.from(ExchangeRate.class);
        query.select(root);
        query.where(
                builder.equal(root.get(ExchangeRate_.date), exchangeRate.getDate()),
                builder.equal(root.get(ExchangeRate_.currencyFrom), exchangeRate.getCurrencyFrom()),
                builder.equal(root.get(ExchangeRate_.currencyTo), exchangeRate.getCurrencyTo())
        );

        if (entityManager.createQuery(query).getResultList().size() > 0) {
            logger.debug("Skipping {}, as it exists already.", exchangeRate);
            return null;
        }

        return item;
    }
}
