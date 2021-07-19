package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminUpdateReq {

    @NotEmpty(message = "【密码】不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotEmpty(message = "【组织Id】不能为空")
    @ApiModelProperty(value = "组织Id", required = true)
    private String groupId;

    @ApiModelProperty(value = "昵称", required = false)
    private String name;

    @ApiModelProperty(value = "手机", required = false)
    private String phone;

}