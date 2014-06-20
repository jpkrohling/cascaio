package com.cascaio.api.v1.admin;

import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class BatchResponse {
    private String name;
    private List<BatchExecution> executions;

    public BatchResponse(String name) {
        this.name = name;
    }

    public BatchResponse(String name, List<BatchExecution> executions) {
        this.name = name;
        this.executions = executions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BatchExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<BatchExecution> executions) {
        this.executions = executions;
    }
}
