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
package com.cascaio.backend.v1.entity.user.adapter;

import com.cascaio.api.v1.user.MutualFundAccountCreateRequest;
import com.cascaio.api.v1.user.MutualFundAccountResponse;
import com.cascaio.api.v1.user.MutualFundAccountUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.MutualFundService;
import com.cascaio.backend.v1.entity.reference.MutualFund;
import com.cascaio.backend.v1.entity.user.MutualFundAccount;
import javax.inject.Inject;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
public class MutualFundAccountAdapter extends
        UserDataAdapter<MutualFundAccountCreateRequest, MutualFundAccountUpdateRequest, MutualFundAccountResponse, MutualFundAccount> {

    @Inject
    MutualFundService mutualFundService;

    @Override
    public MutualFundAccountResponse adaptPersistent(MutualFundAccount persistent) {
        MutualFundAccountResponse response = new MutualFundAccountResponse();
        response.setId(persistent.getId());
        response.setMutualFundId(persistent.getMutualFund().getId());
        response.setName(persistent.getName());
        return response;
    }

    @Override
    public MutualFundAccount adaptUpdate(MutualFundAccountUpdateRequest request, MutualFundAccount toUpdate) {
        String name = request.getName();
        if (isSet(name)) {
            toUpdate.setName(request.getName());
        }

        return toUpdate;
    }

    @Override
    public MutualFundAccount adaptCreate(MutualFundAccountCreateRequest request) {
        MutualFund mutualFund = mutualFundService.readAsEntity(request.getMutualFundId());
        return new MutualFundAccount(getCascaioUser(), request.getName(), mutualFund);
    }

}
