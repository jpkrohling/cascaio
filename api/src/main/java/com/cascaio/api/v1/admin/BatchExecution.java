package com.cascaio.api.v1.admin;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class BatchExecution {
    private String status;
    private String startTime;
    private String endTime;
    private long id;

    public BatchExecution(String status, String startTime, String endTime, long id) {
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
