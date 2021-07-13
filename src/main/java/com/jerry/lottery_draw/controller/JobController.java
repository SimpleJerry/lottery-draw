package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.req.JobCreateReq;
import com.jerry.lottery_draw.req.JobQueryReq;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.resp.JobQueryResp;
import com.jerry.lottery_draw.resp.JobDoResp;
import com.jerry.lottery_draw.service.JobService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    /**
     * 查询Job列表
     *
     * @param req JobQueryReq
     * @return List<JobQueryResp>
     */
    @GetMapping("/")
    public CommonResp<List<JobQueryResp>> list(@RequestBody JobQueryReq req) {
        CommonResp<List<JobQueryResp>> resp = new CommonResp<>();
        resp.setContent(jobService.list(req));
        return resp;
    }

    /**
     * 根据JobId查询Job
     *
     * @param jobId Long
     * @return JobQueryResp
     */
    @GetMapping("/{jobId}")
    public CommonResp<JobQueryResp> list(@PathVariable Long jobId) {
        CommonResp<JobQueryResp> resp = new CommonResp<>();
        resp.setContent(jobService.query(jobId));
        return resp;
    }

    /**
     * 创建Job
     *
     * @param req JobCreateReq
     * @return null
     */
    @PostMapping("/")
    public CommonResp<Object> create(@RequestBody JobCreateReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        jobService.create(req);
        return resp;
    }

    /**
     * 删除Job
     *
     * @param jobId Long
     * @return null
     */
    @DeleteMapping("/{jobId}")
    public CommonResp<Object> delete(@PathVariable Long jobId) {
        CommonResp<Object> resp = new CommonResp<>();
        jobService.delete(jobId);
        return resp;
    }

    /**
     * 进行抽奖事务
     *
     * @param jobId Long
     * @return JobDoResp
     */
    @PostMapping("/{jobId}/do")
    public CommonResp<JobDoResp> doJob(@PathVariable Long jobId) {
        CommonResp<JobDoResp> resp = new CommonResp<>();
        JobDoResp jobDoResp = jobService.doJob(jobId);
        resp.setContent(jobDoResp);
        return resp;
    }

    /**
     * 根据JobId获取获奖人员名单
     *
     * @param jobId Long
     * @return List<String>
     */
    @GetMapping("/list/result/{jobId}")
    public CommonResp<List<String>> listEmployeeId(@PathVariable Long jobId) {
        CommonResp<List<String>> resp = new CommonResp<>();
        resp.setContent(jobService.selectEmployeeIdsByJobId(jobId));
        return resp;
    }
}
