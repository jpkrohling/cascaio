package com.cascaio.backend.v1.boundary.user;

import com.cascaio.api.v1.user.CheckingAccountCreateRequest;
import com.cascaio.api.v1.user.CheckingAccountResponse;
import com.cascaio.api.v1.user.CheckingAccountUpdateRequest;
import com.cascaio.backend.v1.entity.user.CheckingAccount;
import com.cascaio.backend.v1.entity.user.adapter.CheckingAccountAdapter;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/user/checkingAccounts")
@Stateless
@SecurityDomain("keycloak")
public class CheckingAccountService extends
        BaseUserService<
                        CheckingAccountCreateRequest,
                        CheckingAccountUpdateRequest,
                        CheckingAccountResponse,
                        CheckingAccount,
                        CheckingAccountAdapter> {

    @Inject
    CheckingAccountAdapter adapter;

    @Override
    public CheckingAccountAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<CheckingAccount> getPersistentClass() {
        return CheckingAccount.class;
    }
}
