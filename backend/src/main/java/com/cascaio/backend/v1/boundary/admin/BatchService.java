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
package com.cascaio.backend.v1.boundary.admin;

import com.cascaio.api.v1.Error;
import com.cascaio.api.v1.admin.BatchExecution;
import com.cascaio.api.v1.admin.BatchResponse;
import com.cascaio.backend.v1.control.batch.BatchJobStarter;
import com.cascaio.backend.v1.entity.reference.adapter.DateTimeAdapter;
import org.reflections.Reflections;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@RolesAllowed("admin")
@Stateless
@Path("/batch")
public class BatchService {

    @Inject
    DateTimeAdapter dateTimeAdapter;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BatchResponse> list() throws Exception {
        Reflections reflections = new Reflections("com.cascaio.backend.v1.control.batch");
        Set<Class<? extends BatchJobStarter>> jobs = reflections.getSubTypesOf(BatchJobStarter.class);

        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Set<String> knownJobs = jobOperator.getJobNames();
        List<BatchResponse> batchResponseList = new ArrayList<>();
        for (Class<? extends BatchJobStarter> job : jobs) {
            String name = job.newInstance().getJobName();
            if (knownJobs.contains(name)) {
                int executionCount = jobOperator.getJobInstanceCount(name);
                List<JobInstance> jobInstances = jobOperator.getJobInstances(name, 0, executionCount);
                List<BatchExecution> executions = new ArrayList<>();
                for (JobInstance jobInstance : jobInstances) {
                    JobExecution jobExecution = jobOperator.getJobExecution(jobInstance.getInstanceId());

                    String status = jobExecution.getBatchStatus().name();
                    String startTime = dateTimeAdapter.adapt(jobExecution.getStartTime());
                    String endTime = dateTimeAdapter.adapt(jobExecution.getEndTime());
                    long executionId = jobExecution.getExecutionId();
                    executions.add(new BatchExecution(status, startTime, endTime, executionId));
                }
                batchResponseList.add(new BatchResponse(name, executions));
            } else {
                batchResponseList.add(new BatchResponse(name));
            }
        }
        return batchResponseList;
    }

    @DELETE
    @Path("{id}")
    public Response abort(@PathParam("id") long executionId) {
        JobExecution execution = BatchRuntime.getJobOperator().getJobExecution(executionId);

        boolean aborting = false;
        logger.trace("Status: {}", execution.getBatchStatus());

        if (execution.getBatchStatus().equals(BatchStatus.STARTED)) {
            BatchRuntime.getJobOperator().stop(executionId);
            aborting = true;
        }

        if (execution.getBatchStatus().equals(BatchStatus.STARTING)) {
            BatchRuntime.getJobOperator().abandon(executionId);
            aborting = true;
        }

        if (aborting) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("status", "The job is not in STARTING nor STARTED status")).build();
        }
    }

}
