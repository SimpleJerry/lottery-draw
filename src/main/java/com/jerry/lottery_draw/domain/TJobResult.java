package com.jerry.lottery_draw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TJobResult {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long jobId;

    private String groupId;

    private String employeeId;

    private String awardId;

    private Date time;

}