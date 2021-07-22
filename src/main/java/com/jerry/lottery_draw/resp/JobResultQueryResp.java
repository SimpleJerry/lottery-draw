package com.jerry.lottery_draw.resp;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobResultQueryResp {

    private Long jobId;

    private List<Info> infoList;

    @Data
    public static class Info {

        private String groupId;

        private String groupName;

        private String employeeId;

        private String employeeName;

        private String awardId;

        private String awardName;

        private Date time;

    }

}