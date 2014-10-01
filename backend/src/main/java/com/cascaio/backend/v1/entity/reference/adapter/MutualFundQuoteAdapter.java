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

import com.cascaio.api.v1.reference.MutualFundQuoteCreateRequest;
import com.cascaio.api.v1.reference.MutualFundQuoteResponse;
import com.cascaio.api.v1.reference.MutualFundQuoteUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.MutualFundService;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.MutualFund;
import com.cascaio.backend.v1.entity.reference.MutualFundQuote;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class MutualFundQuoteAdapter extends
        EntityAdapter<MutualFundQuoteCreateRequest, MutualFundQuoteUpdateRequest, MutualFundQuoteResponse, MutualFundQuote> {

    @Inject
    MutualFundService mutualFundService;

    @Inject
    LocalDateAdapter dateTimeAdapter;

    @Inject
    BigDecimalAdapter bigDecimalAdapter;

    @Inject
    MutualFundAdapter mutualFundAdapter;

    @Override
    public MutualFundQuoteResponse adaptPersistent(MutualFundQuote mutualFundQuote) {
        MutualFundQuoteResponse response = new MutualFundQuoteResponse();
        response.setPrice(bigDecimalAdapter.adapt(mutualFundQuote.getPrice()));
        response.setDate(dateTimeAdapter.adapt(mutualFundQuote.getDate()));
        response.setMutualFund(mutualFundAdapter.adaptPersistent(mutualFundQuote.getMutualFund()));
        return response;
    }

    @Override
    public MutualFundQuote adaptUpdate(MutualFundQuoteUpdateRequest request, MutualFundQuote toUpdate) {
        if (isSet(request.getPrice())) {
            toUpdate.setPrice(bigDecimalAdapter.adapt(request.getPrice()));
        }
        return toUpdate;
    }

    @Override
    public MutualFundQuote adaptCreate(MutualFundQuoteCreateRequest request) {
        LocalDate date = dateTimeAdapter.adapt(request.getDate());
        BigDecimal price = bigDecimalAdapter.adapt(request.getPrice());
        MutualFund mutualFund = mutualFundService.readAsEntity(request.getMutualFundId());
        return new MutualFundQuote(date, price, mutualFund);
    }
}
