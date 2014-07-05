package com.cascaio.backend.v1.boundary.user;

import com.cascaio.api.v1.BaseQueryRequest;
import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.api.v1.user.CheckingAccountTransactionCreateRequest;
import com.cascaio.api.v1.user.CheckingAccountTransactionResponse;
import com.cascaio.api.v1.user.CheckingAccountTransactionUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.boundary.UserService;
import com.cascaio.backend.v1.entity.user.Account;
import com.cascaio.backend.v1.entity.user.CascaioUser;
import com.cascaio.backend.v1.entity.user.CheckingAccount_;
import com.cascaio.backend.v1.entity.user.CheckingTransaction;
import com.cascaio.backend.v1.entity.user.CheckingTransaction_;
import com.cascaio.backend.v1.entity.user.UserData_;
import com.cascaio.backend.v1.entity.user.adapter.CheckingAccountTransactionAdapter;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
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
        BaseQueryRequest,
        ReadRequestById,
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

    @Override
    public void instrumentQuery(CriteriaBuilder builder, Root<CheckingTransaction> root, CriteriaQuery<CheckingTransaction> query) {
        Join<CheckingTransaction, Account> account = root.join(CheckingTransaction_.account);
        if (null == query.getRestriction()) {
            query.where(builder.equal(account.get(CheckingAccount_.user), getCurrentUser()));
        } else {
            query.where(query.getRestriction(), builder.equal(account.get(CheckingAccount_.user), getCurrentUser()));
        }
    }

    public CascaioUser getCurrentUser() {
        return userService.retrieveOrCreateById(sessionContext.getCallerPrincipal().getName());
    }

}
