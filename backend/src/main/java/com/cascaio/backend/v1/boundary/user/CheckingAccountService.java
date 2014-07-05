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

import com.cascaio.api.v1.BaseQueryRequest;
import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.api.v1.user.CheckingAccountCreateRequest;
import com.cascaio.api.v1.user.CheckingAccountResponse;
import com.cascaio.api.v1.user.CheckingAccountUpdateRequest;
import com.cascaio.backend.v1.entity.user.CheckingAccount;
import com.cascaio.backend.v1.entity.user.Transaction;
import com.cascaio.backend.v1.entity.user.adapter.CheckingAccountAdapter;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/user/checkingAccounts")
@Stateless
public class CheckingAccountService extends BaseUserService<
        CheckingAccountCreateRequest,
        CheckingAccountUpdateRequest,
        BaseQueryRequest,
        ReadRequestById,
        CheckingAccountResponse,
        CheckingAccount,
        CheckingAccountAdapter> {

    @Override
    public void preDelete(ReadRequestById request) {
        CheckingAccount account = getEntityManager().find(CheckingAccount.class, request.getId());
        for (Transaction transaction : account.getTransactions()) {
            getEntityManager().remove(transaction);
        }
    }


}
