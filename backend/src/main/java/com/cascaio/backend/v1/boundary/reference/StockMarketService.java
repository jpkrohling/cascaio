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
import com.cascaio.api.v1.reference.StockMarketCreateRequest;
import com.cascaio.api.v1.reference.StockMarketResponse;
import com.cascaio.api.v1.reference.StockMarketUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.StockMarket;
import com.cascaio.backend.v1.entity.reference.StockMarket_;
import com.cascaio.backend.v1.entity.reference.adapter.StockMarketAdapter;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
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
        BaseQueryRequest,
        ReadRequestById,
        StockMarketResponse,
        StockMarket,
        StockMarketAdapter> {

    @Inject
    Logger logger;

    @Path("/symbol/{symbol}")
    @GET
    @RolesAllowed("admin")
    public StockMarketResponse getBySymbol(@PathParam("symbol") String symbol) {
        return getAdapter().adaptPersistent(getBySymbolAsEntity(symbol));
    }

    @RolesAllowed("admin")
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
