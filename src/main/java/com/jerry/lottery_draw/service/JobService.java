package com.jerry.lottery_draw.service;

import com.jerry.lottery_draw.domain.*;
import com.jerry.lottery_draw.mapper.TJobMapper;
import com.jerry.lottery_draw.mapper.TJobResultMapper;
import com.jerry.lottery_draw.util.SnowFlake;
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

    @Resource
    private SnowFlake snowFlake;

    /**
     * 根据groupId查询其下的所有Job信息
     *
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
     * 在某企业下新增一个Job
     *
     * @param groupId
     * @return
     */
    public TJob createJob(String groupId) {
        // 创建job
        TJob tJob = new TJob();
        tJob.setJobId(snowFlake.nextId());
        tJob.setGroupId(groupId);
        // 插入数据库
        tJobMapper.insertSelective(tJob);
        // 再通过JobId从数据库中查询信息
        TJobExample tJobExample = new TJobExample();
        tJobExample.createCriteria().andJobIdEqualTo(tJob.getJobId());
        return tJobMapper.selectByExample(tJobExample).get(0);
    }

    /**
     * 通过jobId删除Job
     *
     * @param jobId
     */
    public TJob deleteJob(Long jobId) {
        TJobExample tJobExample = new TJobExample();
        tJobExample.createCriteria().andJobIdEqualTo(jobId);
        TJob tJob = tJobMapper.selectByExample(tJobExample).get(0);
        tJobMapper.deleteByPrimaryKey(tJob.getId());
        return tJob;
    }

    /**
     * 根据JobId获取获奖人员名单
     *
     * @param jobId
     * @return
     */
    public List<String> selectEmployeeIdsByJobId(Long jobId) {
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


