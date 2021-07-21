package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AwardAddReq {

    @NotEmpty(message = "【组织Id】不能为空")
    @ApiModelProperty(value = "奖品Id", required = true)
    private String groupId;

    @NotEmpty(message = "【奖品名称】不能为空")
    @ApiModelProperty(value = "奖品名称", required = true)
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
