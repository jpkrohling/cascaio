package com.cascaio.backend.v1.control.batch.stockquote;

import com.cascaio.backend.v1.control.batch.BasicStarter;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/batch/stockQuotes")
@Singleton
public class StockQuoteStarter extends BasicStarter {

    @Inject
    Logger logger;

    @POST
    @Schedule(minute="0", hour="23")
    @RolesAllowed("admin")
    public String start() {
        return doStart();
    }

    @Override
    protected String getJobName() {
        return "stockQuotes";
    }

}
