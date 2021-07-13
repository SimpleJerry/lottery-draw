package com.jerry.lottery_draw.resp;

import com.jerry.lottery_draw.domain.TEmployee;
import lombok.Data;

import java.util.List;

@Data
public class JobDoResp {

    private String awardId;

    private String groupId;

    private Integer remainQuantity;

    private List<TEmployee> userList;

}