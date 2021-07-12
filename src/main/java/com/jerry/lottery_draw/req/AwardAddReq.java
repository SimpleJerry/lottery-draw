package com.jerry.lottery_draw.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AwardAddReq {

    @NotEmpty(message = "【组织Id】不能为空")
    private String groupId;

    private String awardId;

    private String awardName;

}
