package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AwardQueryReq {

    @ApiModelProperty(value = "组织Id", required = false)
    private String groupId;

    @ApiModelProperty(value = "奖品Id", required = false)
    private String awardId;

    @ApiModelProperty(value = "奖品名称", required = false)
    private String awardName;

}
