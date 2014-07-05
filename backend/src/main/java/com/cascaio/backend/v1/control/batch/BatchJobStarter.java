/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cascaio.backend.v1.control.batch;

import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.Metric;
import javax.batch.runtime.StepExecution;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@RolesAllowed("admin")
public abstract class BatchJobStarter {

    @Inject
    Logger logger;

    public abstract String getJobName();

    public String doStart() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        String jobName = getJobName();
        long executionId = jobOperator.start(jobName, new Properties());
        logger.info("Started job {} with execution ID {}", jobName, executionId);
        return "Execution id: " + executionId;
    }

    @GET
    @Path("{executionId}")
    public String status(@PathParam("executionId") long executionId) {
        return doStatus(executionId);
    }

    public String doStatus(@PathParam("executionId") long executionId) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        JobExecution jobExecution = jobOperator.getJobExecution(executionId);
        String status = jobExecution.getBatchStatus().toString();
        if (logger.isInfoEnabled()) {
            logger.info("Status for job {}: {}", executionId, status);

            logger.info("Job name: {}", jobExecution.getJobName());
            logger.info("Started at: {}", jobExecution.getStartTime());
            logger.info("Last update: {}", jobExecution.getLastUpdatedTime());
            logger.info("Finished at: {}", jobExecution.getEndTime());

            List<StepExecution> stepExecutions = jobOperator.getStepExecutions(executionId);
            for (StepExecution stepExecution : stepExecutions) {
                logger.info("Step name: {}", stepExecution.getStepName());
                for (Metric metric : stepExecution.getMetrics()) {
                    logger.info("Metric: {}, value: {}", metric.getType(), metric.getValue());
                }
            }
        }

        return "Status: " + status;
    }

}
