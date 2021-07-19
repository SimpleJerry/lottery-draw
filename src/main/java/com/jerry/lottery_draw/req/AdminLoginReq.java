package com.jerry.lottery_draw.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminLoginReq {

    @NotEmpty(message = "【帐号】不能为空")
    @ApiModelProperty(value = "帐号", required = true)
    private String account;

    @NotEmpty(message = "【密码】不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}