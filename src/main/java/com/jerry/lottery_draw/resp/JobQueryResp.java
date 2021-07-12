package com.jerry.lottery_draw.resp;

import lombok.Data;

import java.util.Date;

@Data
public class JobQueryResp {

    private Long jobId;

    private String groupId;

    private String awardIds;

    private Date time;

}
