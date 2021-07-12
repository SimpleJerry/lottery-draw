package com.jerry.lottery_draw.resp;

import lombok.Data;

@Data
public class AwardQueryResp {

    private String awardId;

    private String awardName;

    private Integer onceQuantity;

    private Integer totalQuantity;

    private Integer remainQuantity;

    private Integer priority;

    private String img;

    private String groupId;

}
