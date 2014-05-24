package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.reference.MutualFundCreateRequest;
import com.cascaio.api.v1.reference.MutualFundResponse;
import com.cascaio.api.v1.reference.MutualFundUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.MutualFund;
import com.cascaio.backend.v1.entity.reference.adapter.MutualFundAdapter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/financialInstitutions")
@Stateless
public class MutualFundService extends BaseService<
        MutualFundCreateRequest,
        MutualFundUpdateRequest,
        MutualFundResponse,
        MutualFund,
        MutualFundAdapter> {

    @Inject
    MutualFundAdapter adapter;

    @Override
    public MutualFundAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<MutualFund> getPersistentClass() {
        return MutualFund.class;
    }
}
