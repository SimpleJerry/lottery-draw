package com.jerry.lottery_draw.domain;

import java.util.Date;

public class THistory {
    private Long id;

    private String jobId;

    private String groupId;

    private String employeeId;

    private String awardId;

    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", jobId=").append(jobId);
        sb.append(", groupId=").append(groupId);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", awardId=").append(awardId);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}