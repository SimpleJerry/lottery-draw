package com.jerry.lottery_draw.req;

import lombok.Data;

@Data
public class AdminUpdateReq {

    private String password;

    private String groupId;

    private String name;

    private String phone;

}