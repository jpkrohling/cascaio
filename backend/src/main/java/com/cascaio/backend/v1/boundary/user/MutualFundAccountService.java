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
import com.cascaio.api.v1.user.MutualFundAccountCreateRequest;
import com.cascaio.api.v1.user.MutualFundAccountResponse;
import com.cascaio.api.v1.user.MutualFundAccountUpdateRequest;
import com.cascaio.backend.v1.entity.user.MutualFundAccount;
import com.cascaio.backend.v1.entity.user.adapter.MutualFundAccountAdapter;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
@Path("/user/mutualFundAccounts")
@Stateless
@RolesAllowed({"user", "admin"})
public class MutualFundAccountService extends BaseUserService<
        MutualFundAccountCreateRequest,
        MutualFundAccountUpdateRequest,
        BaseQueryRequest,
        ReadRequestById,
        MutualFundAccountResponse,
        MutualFundAccount,
        MutualFundAccountAdapter> {

}
