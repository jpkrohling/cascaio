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

import com.cascaio.api.v1.user.CheckingAccountTransactionCreateRequest;
import com.cascaio.api.v1.user.CheckingAccountTransactionQueryRequest;
import com.cascaio.api.v1.user.CheckingAccountTransactionReadRequest;
import com.cascaio.api.v1.user.CheckingAccountTransactionResponse;
import com.cascaio.api.v1.user.CheckingAccountTransactionUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.boundary.UserService;
import com.cascaio.backend.v1.control.DataAccessForbiddenException;
import com.cascaio.backend.v1.entity.user.CascaioUser;
import com.cascaio.backend.v1.entity.user.CheckingAccount;
import com.cascaio.backend.v1.entity.user.CheckingTransaction;
import com.cascaio.backend.v1.entity.user.CheckingTransaction_;
import com.cascaio.backend.v1.entity.user.adapter.CheckingAccountTransactionAdapter;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/user/checkingAccounts/{accountId}/transactions")
@Stateless
@RolesAllowed({"user", "admin"})
public class CheckingAccountTransactionService extends BaseService<
        CheckingAccountTransactionCreateRequest,
        CheckingAccountTransactionUpdateRequest,
        CheckingAccountTransactionQueryRequest,
        CheckingAccountTransactionReadRequest,
        CheckingAccountTransactionResponse,
        CheckingTransaction,
        CheckingAccountTransactionAdapter> {

    @Resource
    SessionContext sessionContext;

    @Inject
    UserService userService;

    @Inject
    CheckingAccountService checkingAccountService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response createAsJson(@Valid CheckingAccountTransactionCreateRequest request) {
        return super.createAsJson(request);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response createAsFormParameters(@Valid @BeanParam CheckingAccountTransactionCreateRequest request) {
        return super.createAsFormParameters(request);
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response delete(@Valid @BeanParam CheckingAccountTransactionReadRequest request) {
        // pre load the account, due to a problem in Hibernate
        checkingAccountService.readAsEntity(request.getAccountId());
        return super.delete(request);
    }

    @Override
    public void instrumentQuery(CriteriaBuilder builder, Root<CheckingTransaction> root, CriteriaQuery<CheckingTransaction> query, CheckingAccountTransactionQueryRequest request) {
        CheckingAccount account = checkingAccountService.readAsEntity(request.getAccountId());
        if (!getCurrentUser().equals(account.getUser())) {
            throw new DataAccessForbiddenException("The data doesn't belongs to you.");
        }

        if (null == query.getRestriction()) {
            query.where(builder.equal(root.get(CheckingTransaction_.account), account));
        } else {
            query.where(query.getRestriction(), builder.equal(root.get(CheckingTransaction_.account), account));
        }
    }

    public CascaioUser getCurrentUser() {
        return userService.retrieveOrCreateById(sessionContext.getCallerPrincipal().getName());
    }
}
