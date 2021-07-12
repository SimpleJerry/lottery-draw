package com.jerry.lottery_draw.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminCreateReq {

    @NotEmpty(message = "【帐号】不能为空")
    private String account;

    @NotEmpty(message = "【密码】不能为空")
    private String password;

    private String groupId;

    private String name;

    private String phone;

}