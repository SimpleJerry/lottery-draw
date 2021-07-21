package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AwardUpdateReq {


    @ApiModelProperty(value = "组织Id", required = false)
    private String groupId;

    @ApiModelProperty(value = "奖品名称", required = false)
    private String awardName;

    @ApiModelProperty(value = "单次抽奖数量", required = false)
    private Integer onceQuantity;

    @ApiModelProperty(value = "奖品总数", required = false)
    private Integer totalQuantity;

    @ApiModelProperty(value = "奖品剩余数量", required = false)
    private Integer remainQuantity;

    @ApiModelProperty(value = "优先级", required = false)
    private Integer priority;

    @ApiModelProperty(value = "图片路径", required = false)
    private String img;

}
