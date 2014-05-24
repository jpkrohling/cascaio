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
