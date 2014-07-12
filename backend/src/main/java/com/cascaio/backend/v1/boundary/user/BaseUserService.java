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
package com.cascaio.backend.v1.boundary.user;

import com.cascaio.api.v1.BaseUpdateRequest;
import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.boundary.UserService;
import com.cascaio.backend.v1.entity.user.CascaioUser;
import com.cascaio.backend.v1.entity.user.UserData;
import com.cascaio.backend.v1.entity.user.UserData_;
import com.cascaio.backend.v1.entity.user.adapter.UserDataAdapter;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@RolesAllowed({"user", "admin"})
public abstract class BaseUserService
        <   CreateRequest,
            UpdateRequest extends BaseUpdateRequest,
            QueryRequest,
            ReadRequest extends ReadRequestById,
            ApiResponse,
            Persistent extends UserData,
            Adapter extends UserDataAdapter<CreateRequest, UpdateRequest, ApiResponse, Persistent>
        > extends BaseService<CreateRequest, UpdateRequest, QueryRequest, ReadRequest, ApiResponse, Persistent, Adapter> {

    @Resource
    SessionContext sessionContext;

    @Inject
    Logger logger;

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response createAsJson(@Valid CreateRequest request) {
        return super.createAsJson(request);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response createAsFormParameters(@Valid @BeanParam CreateRequest request) {
        return super.createAsFormParameters(request);
    }

    @Override
    public Adapter instrumentAdapter(Adapter adapter) {
        adapter.setCascaioUser(getCurrentUser());
        return super.instrumentAdapter(adapter);
    }

    private void instrumentQuery(CriteriaBuilder builder, Root<Persistent> root, CriteriaQuery<Persistent> query) {
        if (null == query.getRestriction()) {
            query.where(builder.equal(root.get(UserData_.user), getCurrentUser()));
        } else {
            query.where(query.getRestriction(), builder.equal(root.get(UserData_.user), getCurrentUser()));
        }
    }

    @Override
    public void instrumentQuery(CriteriaBuilder builder, Root<Persistent> root, CriteriaQuery<Persistent> query, String id) {
        instrumentQuery(builder, root, query);
    }

    @Override
    public void instrumentQuery(CriteriaBuilder builder, Root<Persistent> root, CriteriaQuery<Persistent> query, QueryRequest request) {
        instrumentQuery(builder, root, query);
    }

    public CascaioUser getCurrentUser() {
        return userService.retrieveOrCreateById(sessionContext.getCallerPrincipal().getName());
    }
}
