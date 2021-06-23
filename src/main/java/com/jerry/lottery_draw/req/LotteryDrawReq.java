package com.jerry.lottery_draw.req;

import javax.validation.constraints.NotEmpty;

public class LotteryDrawReq {

    @NotEmpty(message = "【jobId】不能为空")
    private Long jobId;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "LotteryDrawReq{" +
                "jobId=" + jobId +
                '}';
    }
}