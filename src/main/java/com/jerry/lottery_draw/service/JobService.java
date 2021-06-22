package com.jerry.lottery_draw.service;

import com.jerry.lottery_draw.domain.*;
import com.jerry.lottery_draw.mapper.TJobMapper;
import com.jerry.lottery_draw.mapper.TJobResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private static final Logger LOG = LoggerFactory.getLogger(JobService.class);

    @Resource
    private TJobMapper tJobMapper;

    @Resource
    private TJobResultMapper tJobResultMapper;

    /**
     * 根据groupId查询其下的所有Job信息
     * @param groupId
     * @return
     */
    public List<TJob> selectJobByGroupId(String groupId) {
        TJobExample tJobExample = new TJobExample();
        tJobExample.createCriteria().andGroupIdEqualTo(groupId);
        List<TJob> tJobs = tJobMapper.selectByExample(tJobExample);
        return tJobs;
    }

    /**
     * 根据JobId获取获奖人员名单
     * @param jobId
     * @return
     */
    public List<String> selectEmployeeIdsByJobId(String jobId) {
        TJobResultExample tJobResultExample = new TJobResultExample();
        tJobResultExample.createCriteria().andJobIdEqualTo(jobId);
        List<TJobResult> tJobResults = tJobResultMapper.selectByExample(tJobResultExample);
        List<String> employeeIds = new ArrayList<>();
        for (TJobResult tJobResult : tJobResults) {
            employeeIds.add(tJobResult.getEmployeeId());
        }
        return employeeIds;
    }

}


