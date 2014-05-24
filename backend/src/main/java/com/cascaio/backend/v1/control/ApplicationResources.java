package com.cascaio.backend.v1.control;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@ApplicationScoped
public class ApplicationResources {
    @Produces
    @PersistenceContext
    private static javax.persistence.EntityManager em;

    @Produces
    private static DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.basicDateTime();

    @Inject
    Logger logger;

    @Produces
    public static Logger produceLog(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
