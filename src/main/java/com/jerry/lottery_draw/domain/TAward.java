package com.jerry.lottery_draw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TAward {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String awardId;

    private String awardName;

    private Integer onceQuantity;

    private Integer totalQuantity;

    private Integer remainQuantity;

    private Integer priority;

    private String img;

    private String groupId;

    private Date createdAt;

    private Date updatedAt;

}