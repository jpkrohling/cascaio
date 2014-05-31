package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.MutualFundQuoteCreateRequest;
import com.cascaio.api.v1.reference.MutualFundQuoteResponse;
import com.cascaio.api.v1.reference.MutualFundQuoteUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.MutualFundService;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.MutualFund;
import com.cascaio.backend.v1.entity.reference.MutualFundQuote;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class MutualFundQuoteAdapter extends
        EntityAdapter<MutualFundQuoteCreateRequest, MutualFundQuoteUpdateRequest, MutualFundQuoteResponse, MutualFundQuote> {

    @Inject
    MutualFundService mutualFundService;

    @Inject
    DateTimeAdapter dateTimeAdapter;

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
        LocalDate date = dateTimeAdapter.adaptToLocalDate(request.getDate());
        BigDecimal price = bigDecimalAdapter.adapt(request.getPrice());
        MutualFund mutualFund = mutualFundService.readAsEntity(request.getMutualFundId());
        return new MutualFundQuote(date, price, mutualFund);
    }
}
