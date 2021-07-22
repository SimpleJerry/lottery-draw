package com.jerry.lottery_draw.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.jerry.lottery_draw.domain.TJobResult;
import com.jerry.lottery_draw.resp.JobResultQueryResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TJobResultMapper extends BaseMapper<TJobResult> {

    @Select("SELECT job_id, group_id, \n" +
            "( SELECT group_name FROM t_group B WHERE A.group_id = B.group_id ) AS group_name, \n" +
            "employee_id, \n" +
            "( SELECT employee_name FROM t_employee C WHERE A.employee_id = C.employee_id ) AS employee_name, \n" +
            "award_id, ( SELECT award_name FROM t_award D WHERE A.award_id = D.award_id ) AS award_name, \n" +
            "time FROM t_job_result A \n" +
            "${ew.customSqlSegment}")
    List<JobResultQueryResp.Info> selectJobResults(@Param(Constants.WRAPPER) LambdaQueryWrapper<TJobResult> lambdaQueryWrapper);
}