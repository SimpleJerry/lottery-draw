package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JobQueryReq {

    @NotEmpty(message = "【组织Id】不能为空")
    @ApiModelProperty(value = "组织Id", required = true)
    private String groupId;

}
