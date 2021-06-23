package com.jerry.lottery_draw.service;

import cn.hutool.core.lang.Snowflake;
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
        tJob.setJobId(new Snowflake(1, 1).nextId());
        tJob.setGroupId(groupId);
        tJob.setAwardIds("[]");
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

    // /**
    //  * 根据jobId进行抽奖
    //  *
    //  * @param jobId
    //  * @return
    //  */
    // public AwardLotteryDrawResp drawLottery(Long jobId) {
    //     // 通过awardId，查询到相应的award
    //     TAward tAward = selectAwardByAwardId(req.getAwardId());
    //     // 判断是否存在award，如果为null就抛出异常，如果存在就继续执行
    //     if (ObjectUtils.isEmpty(tAward)) {
    //         // 奖品不存在
    //         LOG.info("奖品不存在, awardId:{}", req.getAwardId());
    //         throw new BusinessException(BusinessExceptionCode.AWARD_NOT_EXISTS);
    //     }
    //     else {
    //         // 构造返回体：AwardLotteryDrawResp
    //         // 接下来需要分别填充awardId,remainQuantity和userList
    //         AwardLotteryDrawResp awardLotteryDrawResp = new AwardLotteryDrawResp();
    //         // 【awardId】
    //         awardLotteryDrawResp.setAwardId(req.getAwardId());
    //         // 【groupId】
    //         awardLotteryDrawResp.setGroupId(req.getGroupId());
    //         // 根据award的所剩个数与单次抽取数决定本轮抽几个
    //         if (tAward.getRemainQuantity() <= 0) {
    //             // 当剩余数<=0时，set一个空列表
    //             awardLotteryDrawResp.setUserList(new ArrayList<>());
    //             // 【remainQuantity】
    //             awardLotteryDrawResp.setRemainQuantity(0);
    //         }
    //         else {
    //             if (ObjectUtils.isEmpty(req.getJobId())) {
    //                 // 如果没有穿jobId进来，视为之前没有抽过奖，通过雪花算法生成一个JobId
    //                 req.setJobId(new Snowflake(1, 1).nextIdStr());
    //             }
    //             // 获取中过奖的EmployeeIds
    //             List<String> selectedEmployeeIds = jobService.selectEmployeeIdsByJobId(req.getJobId());
    //             // 获取没中过奖的TEmployee
    //             TEmployeeExample tEmployeeExample = new TEmployeeExample();
    //             if (!ObjectUtils.isEmpty(selectedEmployeeIds)) {
    //                 tEmployeeExample.createCriteria().andEmployeeIdNotIn(selectedEmployeeIds);
    //             }
    //             List<TEmployee> notSelectedEmployees = tEmployeeMapper.selectByExample(tEmployeeExample);
    //             // 本轮中奖的Employees
    //             List<TEmployee> selectedEmployees;
    //             // 当剩余数<单次获取数
    //             if (tAward.getRemainQuantity() < tAward.getOnceQuantity()) {
    //                 selectedEmployees = randomEleList(notSelectedEmployees, tAward.getRemainQuantity());
    //                 tAward.setRemainQuantity(0);
    //             }
    //             // 当剩余数>档次抽取数时
    //             else {
    //                 selectedEmployees = randomEleList(notSelectedEmployees, tAward.getOnceQuantity());
    //                 tAward.setRemainQuantity(tAward.getRemainQuantity() - tAward.getOnceQuantity());
    //             }
    //             // 【remainQuantity】
    //             awardLotteryDrawResp.setRemainQuantity(tAward.getRemainQuantity());
    //             // 【userList】
    //             awardLotteryDrawResp.setUserList(selectedEmployees);
    //             LOG.info("返回体已构造完毕");
    //             // 处理已经抽出的Employee
    //             // t_award
    //             tAwardMapper.updateByPrimaryKey(tAward);
    //             // t_job & t_history
    //             for (TEmployee tEmployee : selectedEmployees) {
    //                 TJob tJob = new TJob();
    //                 tJob.setJobId(req.getJobId());
    //                 tJob.setGroupId(req.getGroupId());
    //                 tJob.setEmployeeId(tEmployee.getEmployeeId());
    //                 tJob.setAwardId(req.getAwardId());
    //                 tJobMapper.insertSelective(tJob);
    //                 THistory tHistory = new THistory();
    //                 BeanUtil.copyProperties(tJob, tHistory);
    //                 tHistoryMapper.insertSelective(tHistory);
    //             }
    //         }
    //         return awardLotteryDrawResp;
    //     }
    // }

}


