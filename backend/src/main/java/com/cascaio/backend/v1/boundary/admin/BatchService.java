package com.cascaio.backend.v1.boundary.admin;

import com.cascaio.api.v1.admin.BatchExecution;
import com.cascaio.api.v1.admin.BatchResponse;
import com.cascaio.backend.v1.control.batch.BatchJobStarter;
import com.cascaio.backend.v1.entity.reference.adapter.DateTimeAdapter;
import org.reflections.Reflections;

import javax.annotation.security.RolesAllowed;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

}
