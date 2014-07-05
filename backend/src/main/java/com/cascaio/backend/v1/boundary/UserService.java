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

package com.cascaio.backend.v1.boundary;

import com.cascaio.backend.v1.entity.user.CascaioUser;
import com.cascaio.backend.v1.entity.user.CascaioUser_;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
public class UserService {

    @Inject
    Logger logger;

    @Inject
    private EntityManager entityManager;

    public CascaioUser retrieveOrCreateById(String id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CascaioUser> query = builder.createQuery(CascaioUser.class);
        Root<CascaioUser> root = query.from(CascaioUser.class);
        query.select(root);
        query.where(builder.equal(root.get(CascaioUser_.id), id));

        List<CascaioUser> results = entityManager.createQuery(query).getResultList();
        if (results.size() == 1) {
            logger.debug("User {} is registered already, returning it to the caller", id);
            return results.get(0);
        }

        if (results.size() >= 1) {
            throw new IllegalStateException("Duplicate user id found.");
        }

        logger.debug("User doesn't exists, creating user with ID {}", id);
        CascaioUser cascaioUser = new CascaioUser(id);
        entityManager.persist(cascaioUser);
        return cascaioUser;
    }

}
