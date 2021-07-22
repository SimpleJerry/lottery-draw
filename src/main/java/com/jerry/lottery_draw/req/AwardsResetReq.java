package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AwardsResetReq {

    @NotEmpty(message = "【奖品Id列表】不能为空")
    @ApiModelProperty(value = "奖品Id列表", required = true)
    private List<String> awardIds;

}
