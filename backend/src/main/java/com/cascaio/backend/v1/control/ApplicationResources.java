package com.cascaio.backend.v1.control;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.inject.Singleton;
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

    @Produces @Singleton
    public HttpClient produceHttpClient() {
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder.setConnectTimeout(5000);
        requestConfigBuilder.setSocketTimeout(5000);
        requestConfigBuilder.setStaleConnectionCheckEnabled(true);

        return HttpClientBuilder
                .create()
                .setMaxConnTotal(100)
                .setDefaultRequestConfig(requestConfigBuilder.build())
                .build();
    }
}
