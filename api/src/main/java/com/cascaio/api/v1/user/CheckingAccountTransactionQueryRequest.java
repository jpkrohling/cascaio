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

package com.cascaio.api.v1.user;

import com.cascaio.api.v1.BaseQueryRequest;
import javax.ws.rs.PathParam;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
public class CheckingAccountTransactionQueryRequest extends BaseQueryRequest {

    @PathParam("accountId")
    private String accountId;

    public CheckingAccountTransactionQueryRequest() {
    }

    public CheckingAccountTransactionQueryRequest(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
