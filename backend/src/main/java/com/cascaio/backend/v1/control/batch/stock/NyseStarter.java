package com.cascaio.backend.v1.control.batch.stock;

import com.cascaio.backend.v1.control.batch.BasicStarter;
import org.slf4j.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/batch/nyse")
@Singleton
public class NyseStarter extends BasicStarter {
    @Inject
    Logger logger;

    @POST
    @Schedule(minute="0", hour="23")
    public String start() {
        return doStart();
    }

    @Override
    protected String getJobName() {
        return "nyse";
    }

}
