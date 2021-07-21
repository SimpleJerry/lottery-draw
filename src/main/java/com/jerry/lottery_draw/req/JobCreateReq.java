package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class JobCreateReq {

    @NotEmpty(message = "【组织Id】不能为空")
    @ApiModelProperty(value = "组织Id", required = true)
    private String groupId;

    @ApiModelProperty(value = "奖品Id列表", required = false)
    private List<String> awardIds;

}
