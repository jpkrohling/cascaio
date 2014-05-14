package com.cascaio.backend.v1.boundary;

import com.cascaio.api.v1.SavingsAccountCreateRequest;
import com.cascaio.api.v1.SavingsAccountResponse;
import com.cascaio.api.v1.SavingsAccountUpdateRequest;
import com.cascaio.backend.v1.entity.SavingsAccount;
import com.cascaio.backend.v1.entity.adapters.api.SavingsAccountAdapter;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/user/savingsAccounts")
@Stateless
@SecurityDomain("keycloak")
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
