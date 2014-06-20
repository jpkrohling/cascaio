package com.cascaio.backend.v1.control.batch.exchangerate;

import com.cascaio.backend.v1.control.batch.BatchJobStarter;
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
@Path("/batch/exchangeRates")
@Singleton
public class ExchangeRateStarter extends BatchJobStarter {

    @Inject
    Logger logger;

    @POST
    @Schedule(minute="0", hour="23")
    @RolesAllowed("admin")
    public String start() {
        return doStart();
    }

    @Override
    public String getJobName() {
        return "exchangeRates";
    }
}
