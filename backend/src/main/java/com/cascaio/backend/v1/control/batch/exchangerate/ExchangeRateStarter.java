package com.cascaio.backend.v1.control.batch.exchangerate;

import org.slf4j.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.Metric;
import javax.batch.runtime.StepExecution;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/batch/exchangeRate")
@Stateless
public class ExchangeRateStarter {

    @Inject
    Logger logger;

    @GET
    public String start() {
        logger.trace("Starting job exchangeRates");
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long execID = jobOperator.start("exchangeRates", new Properties());
        return "Execution id: " + execID;
    }

    @GET
    @Path("{execID}")
    public String status(@PathParam("execID") long execID) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        JobExecution jobExec = jobOperator.getJobExecution(execID);
        String status = jobExec.getBatchStatus().toString();
        logger.info("Status for job {}: {}", execID, status);
        List<StepExecution> stepExecutions = jobOperator.getStepExecutions(execID);
        for (StepExecution stepExecution : stepExecutions) {
            logger.info("Step name: {}", stepExecution.getStepName());
            if (stepExecution.getStepName().equals("exchangeRateSingleStep")) {
                for (Metric metric : stepExecution.getMetrics()) {
                    logger.info("Metric: {}, value: {}", metric.getType(), metric.getValue());
                }
            }
        }

        return "Status: " + status;
    }

}
