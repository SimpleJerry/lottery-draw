package com.jerry.lottery_draw.req;

import javax.validation.constraints.NotEmpty;

public class AdminLoginReq {

    @NotEmpty(message = "【帐号】不能为空")
    private String account;

    @NotEmpty(message = "【密码】不能为空")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminLoginReq{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}