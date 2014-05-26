package com.cascaio.backend.v1.control.batch.exchangerate;

import org.slf4j.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.Metric;
import javax.batch.runtime.StepExecution;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/batch/exchangeRate")
@Singleton
public class ExchangeRateStarter {

    @Inject
    Logger logger;

    @POST
    @Schedule(minute="0", hour="23")
    public String start() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long executionId = jobOperator.start("exchangeRates", new Properties());
        logger.info("Started job exchangeRates with execution ID {}", executionId);
        return "Execution id: " + executionId;
    }

    @GET
    @Path("{executionId}")
    public String status(@PathParam("executionId") long executionId) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        JobExecution jobExecution = jobOperator.getJobExecution(executionId);
        String status = jobExecution.getBatchStatus().toString();
        if (logger.isInfoEnabled()) {
            logger.info("Status for job {}: {}", executionId, status);
            List<StepExecution> stepExecutions = jobOperator.getStepExecutions(executionId);
            for (StepExecution stepExecution : stepExecutions) {
                logger.info("Step name: {}", stepExecution.getStepName());
                if (stepExecution.getStepName().equals("exchangeRateSingleStep")) {
                    for (Metric metric : stepExecution.getMetrics()) {
                        logger.info("Metric: {}, value: {}", metric.getType(), metric.getValue());
                    }
                }
            }
        }

        return "Status: " + status;
    }

}
