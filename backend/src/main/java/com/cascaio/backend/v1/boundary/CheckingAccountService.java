package com.cascaio.backend.v1.boundary;

import com.cascaio.api.v1.CheckingAccountCreateRequest;
import com.cascaio.api.v1.CheckingAccountResponse;
import com.cascaio.api.v1.CheckingAccountUpdateRequest;
import com.cascaio.backend.v1.entity.CheckingAccount;
import com.cascaio.backend.v1.entity.adapters.api.CheckingAccountAdapter;
import org.jboss.ejb3.annotation.SecurityDomain;
import org.slf4j.Logger;

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
