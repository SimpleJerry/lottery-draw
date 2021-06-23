package com.jerry.lottery_draw.domain;

import java.util.Date;

public class TJob {
    private Long id;

    private Long jobId;

    private String groupId;

    private String awardIds;

    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAwardIds() {
        return awardIds;
    }

    public void setAwardIds(String awardIds) {
        this.awardIds = awardIds;
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
        sb.append(", awardIds=").append(awardIds);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}