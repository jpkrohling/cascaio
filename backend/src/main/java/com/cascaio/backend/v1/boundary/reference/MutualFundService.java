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

import com.cascaio.api.v1.BaseQueryRequest;
import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.api.v1.reference.MutualFundCreateRequest;
import com.cascaio.api.v1.reference.MutualFundResponse;
import com.cascaio.api.v1.reference.MutualFundUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.MutualFund;
import com.cascaio.backend.v1.entity.reference.MutualFund_;
import com.cascaio.backend.v1.entity.reference.adapter.MutualFundAdapter;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/mutualFunds")
@Stateless
public class MutualFundService extends BaseService<
        MutualFundCreateRequest,
        MutualFundUpdateRequest,
        BaseQueryRequest,
        ReadRequestById,
        MutualFundResponse,
        MutualFund,
        MutualFundAdapter> {

    @Inject
    Logger logger;

    @RolesAllowed("admin")
    public MutualFund getByIsinAsEntity(String isin) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MutualFund> query = builder.createQuery(MutualFund.class);
        Root<MutualFund> root = query.from(MutualFund.class);
        query.select(root);
        query.where(
                builder.equal(root.get(MutualFund_.isin), isin)
        );

        List<MutualFund> mutualFundList = getEntityManager().createQuery(query).getResultList();

        if (mutualFundList.size() > 1) {
            String message = "More than one mutual fund found for isin " + isin;
            logger.warn(message);
            throw new NonUniqueResultException(message);
        }

        if (mutualFundList.size() == 0) {
            return null;
        }

        return mutualFundList.get(0);
    }
}
