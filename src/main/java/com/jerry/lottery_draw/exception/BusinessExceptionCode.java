package com.jerry.lottery_draw.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    VOTE_REPEAT("您已点赞过"),
    AWARD_NOT_EXISTS("奖品不存在"),
    JOB_NOT_EXISTS("事务不存在"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
