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
package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.MutualFundCreateRequest;
import com.cascaio.api.v1.reference.MutualFundResponse;
import com.cascaio.api.v1.reference.MutualFundUpdateRequest;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.MutualFund;
import org.joda.money.CurrencyUnit;

import javax.inject.Inject;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class MutualFundAdapter extends
        EntityAdapter<MutualFundCreateRequest, MutualFundUpdateRequest, MutualFundResponse, MutualFund> {

    @Inject
    CurrencyAdapter currencyAdapter;

    @Override
    public MutualFundResponse adaptPersistent(MutualFund mutualFund) {
        MutualFundResponse response = new MutualFundResponse();
        response.setId(mutualFund.getId());
        response.setName(mutualFund.getName());
        response.setCurrency(currencyAdapter.adapt(mutualFund.getCurrency()));
        response.setIsin(mutualFund.getIsin());
        response.setWkn(mutualFund.getWkn());
        return response;
    }

    @Override
    public MutualFund adaptUpdate(MutualFundUpdateRequest request, MutualFund toUpdate) {
        if (isSet(request.getWkn())) {
            toUpdate.setWkn(request.getWkn());
        }

        if (isSet(request.getName())) {
            toUpdate.setName(request.getName());
        }

        if (isSet(request.getCurrency())) {
            toUpdate.setCurrency(currencyAdapter.adapt(request.getCurrency()));
        }

        return toUpdate;
    }

    @Override
    public MutualFund adaptCreate(MutualFundCreateRequest request) {
        CurrencyUnit currencyUnit = currencyAdapter.adapt(request.getCurrency());
        MutualFund mutualFund = new MutualFund(request.getName(), request.getIsin(), currencyUnit);
        mutualFund.setWkn(request.getWkn());
        return mutualFund;
    }
}
