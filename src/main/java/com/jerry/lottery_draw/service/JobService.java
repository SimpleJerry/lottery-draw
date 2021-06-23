package com.jerry.lottery_draw.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.jerry.lottery_draw.domain.*;
import com.jerry.lottery_draw.mapper.*;
import com.jerry.lottery_draw.resp.LotteryDrawResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.hutool.core.util.RandomUtil.randomEleList;

@Service
public class JobService {

    private static final Logger LOG = LoggerFactory.getLogger(JobService.class);

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
     * @param jobId
     * @return
     */
    public TJob selectJobByJobId(Long jobId) {
        TJobExample tJobExample = new TJobExample();
        tJobExample.createCriteria().andJobIdEqualTo(jobId);
        List<TJob> tJobs = tJobMapper.selectByExample(tJobExample);
        if (ObjectUtils.isEmpty(tJobs)) {
            return null;
        }
        else {
            return tJobs.get(0);
        }
    }

    /**
     * 根据groupId查询其下的所有Job信息
     *
     * @param groupId
     * @return
     */
    public List<TJob> listJob(String groupId) {
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
        tJob.setJobId(new Snowflake(1, 1).nextId());
        tJob.setGroupId(groupId);
        tJob.setAwardIds("[]");
        // 插入数据库
        tJobMapper.insertSelective(tJob);
        // 再通过JobId从数据库中查询信息
        return selectJobByJobId(tJob.getJobId());
    }

    /**
     * 通过jobId删除Job
     *
     * @param jobId
     */
    public TJob deleteJob(Long jobId) {
        TJob tJob = selectJobByJobId(jobId);
        tJobMapper.deleteByPrimaryKey(tJob.getId());
        return tJob;
    }

    /**
     * 根据jobId进行抽奖
     *
     * @param jobId
     * @return
     */
    public LotteryDrawResp drawLottery(Long jobId) {
        //  构造返回体：LotteryDrawResp-接下来需要分别填充awardId,groupId,remainQuantity和userList
        LotteryDrawResp lotteryDrawResp = new LotteryDrawResp();

        // 通过jobId获取award_ids
        TJob tJob = selectJobByJobId(jobId);
        List<String> awardIds = Arrays.asList(tJob.getAwardIds().split(","));
        // 根据awardIds查询所有相关奖品信息
        TAwardExample tAwardExample = new TAwardExample();
        tAwardExample.createCriteria().andAwardIdIn(awardIds);
        tAwardExample.setOrderByClause("priority ASC");
        List<TAward> tAwards = tAwardMapper.selectByExample(tAwardExample);
        for (TAward tAward : tAwards) {
            if (tAward.getRemainQuantity() <= 0) {
                // 如果无剩余则跳过该奖品的抽奖
                continue;
            }
            else {
                lotteryDrawResp.setAwardId(tAward.getAwardId());
                lotteryDrawResp.setGroupId(tAward.getGroupId());
                // 根据jobId获取中过奖的EmployeeIds，并获取没中过奖的TEmployee
                List<String> selectedEmployeeIds = selectEmployeeIdsByJobId(jobId);
                TEmployeeExample tEmployeeExample = new TEmployeeExample();
                tEmployeeExample.createCriteria().andEmployeeIdNotIn(selectedEmployeeIds);
                List<TEmployee> notSelectedEmployees = tEmployeeMapper.selectByExample(tEmployeeExample);
                // 本轮中奖的Employees
                Integer drawQuantity = (tAward.getRemainQuantity() < tAward.getOnceQuantity()) ? tAward.getRemainQuantity() : tAward.getOnceQuantity();
                List<TEmployee> selectedEmployees = randomEleList(notSelectedEmployees, drawQuantity);
                // 填充剩余字段
                lotteryDrawResp.setRemainQuantity(tAward.getRemainQuantity());
                lotteryDrawResp.setUserList(selectedEmployees);
                LOG.info("返回体已构造完毕");
                // 保存对tAward的更改
                tAward.setRemainQuantity(tAward.getRemainQuantity() - drawQuantity);
                tAwardMapper.updateByPrimaryKey(tAward);
                // 最后将抽奖结果存入t_job_result和t_history
                for (TEmployee tEmployee : selectedEmployees) {
                    // t_job_result
                    TJobResult tJobResult = new TJobResult();
                    tJobResult.setJobId(tJob.getJobId());
                    tJobResult.setGroupId(tJob.getGroupId());
                    tJobResult.setEmployeeId(tEmployee.getEmployeeId());
                    tJobResult.setAwardId(tAward.getAwardId());
                    tJobResultMapper.insertSelective(tJobResult);
                    // t_history
                    THistory tHistory = new THistory();
                    BeanUtil.copyProperties(tJobResult, tHistory);
                    tHistoryMapper.insertSelective(tHistory);
                }
                break;
            }
        }

        return lotteryDrawResp;
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


