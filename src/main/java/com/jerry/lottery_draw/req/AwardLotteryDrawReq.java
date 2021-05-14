package com.jerry.lottery_draw.req;

import java.util.Date;

public class AwardLotteryDrawReq {
    private String jobId;

    private String awardId;

    private String groupId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "AwardLotteryDrawReq{" +
                "jobId='" + jobId + '\'' +
                ", awardId='" + awardId + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}