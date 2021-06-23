package com.jerry.lottery_draw.resp;

import com.jerry.lottery_draw.domain.TEmployee;

import java.util.List;

public class LotteryDrawResp {
    private String awardId;

    private String groupId;

    private Integer remainQuantity;

    private List<TEmployee> userList;

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(Integer remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public List<TEmployee> getUserList() {
        return userList;
    }

    public void setUserList(List<TEmployee> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "LotteryDrawResp{" +
                "awardId='" + awardId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", remainQuantity=" + remainQuantity +
                ", userList=" + userList +
                '}';
    }
}