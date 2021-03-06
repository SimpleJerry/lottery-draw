package com.jerry.lottery_draw.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jerry.lottery_draw.domain.*;
import com.jerry.lottery_draw.exception.BusinessException;
import com.jerry.lottery_draw.exception.BusinessExceptionCode;
import com.jerry.lottery_draw.mapper.*;
import com.jerry.lottery_draw.req.JobCreateReq;
import com.jerry.lottery_draw.req.JobQueryReq;
import com.jerry.lottery_draw.resp.JobDoResp;
import com.jerry.lottery_draw.resp.JobQueryResp;
import com.jerry.lottery_draw.resp.JobResultQueryResp;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hutool.core.util.RandomUtil.randomEleList;

@Service
public class JobService {

    private static final Logger log = LoggerFactory.getLogger(JobService.class);

    @Resource
    private TJobMapper tJobMapper;

    @Resource
    private TJobResultMapper tJobResultMapper;

    @Resource
    private TAwardMapper tAwardMapper;

    @Resource
    private TEmployeeMapper tEmployeeMapper;

    @Resource
    private THistoryMapper tHistoryMapper;

    /**
     * 辅助函数：根据JobId寻找Job
     *
     * @param jobId Long
     * @return TJob
     */
    public TJob selectJobByJobId(Long jobId) {
        LambdaQueryWrapper<TJob> sqlWhereWrapper = new LambdaQueryWrapper<TJob>()
                .eq(ObjectUtils.isNotEmpty(jobId), TJob::getJobId, jobId);
        return tJobMapper.selectOne(sqlWhereWrapper);
    }

    /**
     * 查询全部Job
     *
     * @param req JobQueryReq
     * @return List<JobQueryResp>
     */
    public List<JobQueryResp> list(JobQueryReq req) {
        LambdaQueryWrapper<TJob> sqlWhereWrapper = new LambdaQueryWrapper<TJob>()
                .likeRight(StringUtils.isNotBlank(req.getGroupId()), TJob::getGroupId, req.getGroupId());
        List<TJob> tJobs = tJobMapper.selectList(sqlWhereWrapper);
        // Bean转换
        List<JobQueryResp> res = new ArrayList<>();
        for (TJob job : tJobs) {
            JobQueryResp resItem = new JobQueryResp();
            BeanUtils.copyProperties(job, resItem);
            res.add(resItem);
        }
        return res;
    }

    /**
     * 查询单个Job
     *
     * @param jobId Long
     * @return JobQueryResp
     */
    public JobQueryResp query(Long jobId) {
        TJob tJob = selectJobByJobId(jobId);
        if (ObjectUtils.isEmpty(tJob)) {
            // Job不存在
            return null;
        }
        else {
            // Job存在
            JobQueryResp res = new JobQueryResp();
            BeanUtils.copyProperties(tJob, res);
            return res;
        }
    }

    /**
     * 创建Job
     *
     * @param req JobCreateReq
     */
    public void create(JobCreateReq req) {
        // 创建job
        TJob tJob = new TJob();
        tJob.setJobId(new Snowflake(1, 1).nextId());
        tJob.setGroupId(req.getGroupId());
        tJob.setAwardIds("[" + String.join(",", req.getAwardIds()) + "]");
        // 插入数据库
        tJobMapper.insert(tJob);
    }

    /**
     * 删除Job
     *
     * @param jobId Long
     */
    public void delete(Long jobId) {
        TJob tJob = selectJobByJobId(jobId);
        if (ObjectUtils.isEmpty(tJob)) {
            log.info("事务不存在：{}", jobId);
            throw new BusinessException(BusinessExceptionCode.JOB_NOT_EXISTS);
        }
        tJobMapper.deleteById(tJob.getId());
    }

    /**
     * 进行抽奖事务
     *
     * @param jobId Long
     * @return JobDoResp
     */
    public JobDoResp doJob(Long jobId) {
        // 通过jobId获取awardIds
        TJob tJob = selectJobByJobId(jobId);
        if (ObjectUtils.isEmpty(tJob)) {
            log.info("事务不存在：{}", jobId);
            throw new BusinessException(BusinessExceptionCode.JOB_NOT_EXISTS);
        }
        List<String> awardIds = Arrays.asList(tJob.getAwardIds().replaceAll("[\\[\\]]", "").split(","));

        // 根据awardIds查询所有相关奖品信息
        LambdaQueryWrapper<TAward> sqlWhereWrapper = new LambdaQueryWrapper<TAward>()
                .in(TAward::getAwardId, awardIds)
                .orderByAsc(TAward::getPriority);
        List<TAward> tAwards = tAwardMapper.selectList(sqlWhereWrapper);

        // 遍历这些awards，直到运行完这个Job
        // 构造返回体：JobDoResp-接下来需要分别填充awardId,groupId,remainQuantity和userList
        JobDoResp jobDoResp = new JobDoResp();
        for (TAward tAward : tAwards) {
            // 如果无剩余则会自动跳过
            if (tAward.getRemainQuantity() > 0) {
                // 根据jobId获取中过奖的EmployeeIds，并获取没中过奖的TEmployee
                LambdaQueryWrapper<TJobResult> selectEmployeeWrapper = new LambdaQueryWrapper<TJobResult>()
                        .eq(ObjectUtils.isNotEmpty(jobId), TJobResult::getJobId, jobId);
                List<String> hasSelectedEmployeeIds = tJobResultMapper.selectList(selectEmployeeWrapper).
                        stream()
                        .map(TJobResult::getEmployeeId)
                        .collect(Collectors.toList());
                LambdaQueryWrapper<TEmployee> employeeSqlWhereWrapper = new LambdaQueryWrapper<TEmployee>()
                        .notIn(ObjectUtils.isNotEmpty(hasSelectedEmployeeIds), TEmployee::getEmployeeId, hasSelectedEmployeeIds);
                List<TEmployee> neverSelectedEmployees = tEmployeeMapper.selectList(employeeSqlWhereWrapper);
                // 本轮中奖的Employees
                Integer drawQuantity = (tAward.getRemainQuantity() < tAward.getOnceQuantity()) ? tAward.getRemainQuantity() : tAward.getOnceQuantity();
                List<TEmployee> selectedEmployees = randomEleList(neverSelectedEmployees, drawQuantity);
                // 填充字段
                BeanUtils.copyProperties(tAward, jobDoResp);
                jobDoResp.setRemainQuantity(tAward.getRemainQuantity() - drawQuantity);
                jobDoResp.setUserList(selectedEmployees);
                log.info("返回体已构造完毕");
                // 保存对tAward的更改
                tAward.setRemainQuantity(tAward.getRemainQuantity() - drawQuantity);
                tAwardMapper.updateById(tAward);
                // 最后将抽奖结果存入t_job_result和t_history
                for (TEmployee tEmployee : selectedEmployees) {
                    // t_job_result
                    TJobResult tJobResult = new TJobResult();
                    tJobResult.setJobId(tJob.getJobId());
                    tJobResult.setGroupId(tJob.getGroupId());
                    tJobResult.setEmployeeId(tEmployee.getEmployeeId());
                    tJobResult.setAwardId(tAward.getAwardId());
                    tJobResultMapper.insert(tJobResult);
                    // t_history
                    THistory tHistory = new THistory();
                    BeanUtil.copyProperties(tJobResult, tHistory);
                    tHistoryMapper.insert(tHistory);
                }
                break;
            }
        }
        return jobDoResp;
    }

    /**
     * 查询抽奖结果
     *
     * @param jobId Long
     * @return List<TJobResult>
     */
    public JobResultQueryResp queryJobResult(Long jobId) {
        JobResultQueryResp res = new JobResultQueryResp();
        res.setJobId(jobId);
        LambdaQueryWrapper<TJobResult> sqlWhereWrapper = new LambdaQueryWrapper<TJobResult>()
                .eq(ObjectUtils.isNotEmpty(jobId), TJobResult::getJobId, jobId);
        List<JobResultQueryResp.Info> infoList = tJobResultMapper.selectJobResults(sqlWhereWrapper);
        res.setInfoList(infoList);
        return res;
    }

}


