package com.cascaio.backend.v1.boundary.user;

import com.cascaio.api.v1.user.SavingsAccountCreateRequest;
import com.cascaio.api.v1.user.SavingsAccountResponse;
import com.cascaio.api.v1.user.SavingsAccountUpdateRequest;
import com.cascaio.backend.v1.entity.user.SavingsAccount;
import com.cascaio.backend.v1.entity.user.adapter.SavingsAccountAdapter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/user/savingsAccounts")
@Stateless
public class SavingsAccountService extends BaseUserService<
        SavingsAccountCreateRequest,
        SavingsAccountUpdateRequest,
        SavingsAccountResponse,
        SavingsAccount,
        SavingsAccountAdapter> {

    @Inject
    SavingsAccountAdapter adapter;

    @Override
    public SavingsAccountAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<SavingsAccount> getPersistentClass() {
        return SavingsAccount.class;
    }
}
