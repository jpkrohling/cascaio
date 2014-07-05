/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.api.v1.reference.MutualFundQuoteCreateRequest;
import com.cascaio.api.v1.reference.MutualFundQuoteQueryRequest;
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

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/mutualFunds/{fundId}/quotes")
@Stateless
public class MutualFundQuoteService extends BaseService<
        MutualFundQuoteCreateRequest,
        MutualFundQuoteUpdateRequest,
        MutualFundQuoteQueryRequest,
        ReadRequestById,
        MutualFundQuoteResponse,
        MutualFundQuote,
        MutualFundQuoteAdapter> {

    @Inject
    Logger logger;

    @Inject
    DateTimeAdapter dateTimeAdapter;

    @Inject
    MutualFundService mutualFundService;

    @Context
    UriInfo uriInfo;

    @Override
    @RolesAllowed("admin")
    public List<MutualFundQuote> listAsEntity(MutualFundQuoteQueryRequest request) {
        MutualFund mutualFund = mutualFundService.readAsEntity(request.getFundId());
        return getByMutualFundAsEntity(mutualFund);
    }

    @RolesAllowed("admin")
    public List<MutualFundQuote> getByMutualFundAsEntity(MutualFund mutualFund) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MutualFundQuote> query = builder.createQuery(MutualFundQuote.class);
        Root<MutualFundQuote> root = query.from(MutualFundQuote.class);
        query.select(root);
        query.where(
                builder.equal(root.get(MutualFundQuote_.mutualFund), mutualFund)
        );

        return getEntityManager().createQuery(query).getResultList();
    }

    @RolesAllowed("admin")
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
}
