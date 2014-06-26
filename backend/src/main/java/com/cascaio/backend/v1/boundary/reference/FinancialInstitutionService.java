package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.BaseQueryRequest;
import com.cascaio.api.v1.BaseReadRequest;
import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.api.v1.admin.FinancialInstitutionImportBundesbankRequest;
import com.cascaio.api.v1.admin.FinancialInstitutionImportBundesbankResponse;
import com.cascaio.api.v1.reference.FinancialInstitutionCreateRequest;
import com.cascaio.api.v1.reference.FinancialInstitutionResponse;
import com.cascaio.api.v1.reference.FinancialInstitutionUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.FinancialInstitution;
import com.cascaio.backend.v1.entity.reference.FinancialInstitution_;
import com.cascaio.backend.v1.entity.reference.adapter.FinancialInstitutionAdapter;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/financialInstitutions")
@Stateless
public class FinancialInstitutionService extends BaseService<
        FinancialInstitutionCreateRequest,
        FinancialInstitutionUpdateRequest,
        BaseQueryRequest,
        ReadRequestById,
        FinancialInstitutionResponse,
        FinancialInstitution,
        FinancialInstitutionAdapter> {

    @Inject
    FinancialInstitutionAdapter adapter;

    @Inject
    Logger logger;

    @GET
    @Path("de/{bic}")
    @RolesAllowed({"admin", "user"})
    public FinancialInstitutionResponse getByBic(@PathParam("bic") String bic) {
        return adapter.adaptPersistent(getByBicAsEntity(bic));
    }

    @RolesAllowed({"admin", "user"})
    public FinancialInstitution getByBicAsEntity(@PathParam("bic") String bic) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FinancialInstitution> query = builder.createQuery(FinancialInstitution.class);
        Root<FinancialInstitution> root = query.from(FinancialInstitution.class);
        query.select(root);
        query.where(builder.equal(root.get(FinancialInstitution_.bic), bic));

        List<FinancialInstitution> financialInstitutionList = getEntityManager().createQuery(query).getResultList();

        if (financialInstitutionList.size() > 1) {
            String message = "More than one financial institution found with BIC " + bic;
            logger.warn(message);
            throw new IllegalStateException(message);
        }

        if (financialInstitutionList.size() == 0) {
            return null;
        }

        return financialInstitutionList.get(0);
    }

    @POST
    @Path("import/bundesbank")
    @RolesAllowed("admin")
    public Response importFileFromLocation(FinancialInstitutionImportBundesbankRequest request) {
        InputStream contents = null;
        try {
            contents = new URL(request.getLocation()).openStream();
        } catch (IOException e) {
            logger.error("IOException: " + e.getMessage());
            return Response.serverError().entity(new FinancialInstitutionImportBundesbankResponse(e.getMessage())).build();
        }
        InputStreamReader isr = new InputStreamReader(contents, Charset.forName("Windows-1252"));
        BufferedReader in = new BufferedReader(isr);

        String line;
        try {
            int success = 0;
            int skipped = 0;
            while ((line = in.readLine()) != null) {
                String blz = line.substring(0, 8).trim();
                String merkmal = line.substring(8, 8+1).trim();
                String bezeichnung = line.substring(9, 9+58).trim();
                String plz = line.substring(67, 67+5).trim();
                String ort = line.substring(72, 72+35).trim();
                String kurzbezeichnung = line.substring(107, 107+27).trim();
                String pan = line.substring(134, 134+5).trim();
                String bic = line.substring(139, 139+11).trim();

                if (!bic.isEmpty() && !blz.isEmpty()) {

                    FinancialInstitution existing = getByBicAsEntity(bic);

                    if (null == existing) {
                        FinancialInstitution financialInstitution = new FinancialInstitution(kurzbezeichnung, "DE");
                        financialInstitution.setBankleitzahl(blz);
                        financialInstitution.setBic(bic);
                        getEntityManager().persist(financialInstitution);
                        success++;
                    } else {
                        skipped++;
                    }
                }
            }

            return Response.ok().entity(new FinancialInstitutionImportBundesbankResponse(success, skipped)).build();
        } catch (IOException e) {
            logger.error("IOException: " + e.getMessage());
            return Response.serverError().entity(new FinancialInstitutionImportBundesbankResponse(e.getMessage())).build();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.warn("Failed to close the stream.");
            }
        }
    }
}
