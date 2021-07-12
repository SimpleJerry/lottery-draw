package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.domain.TJob;
import com.jerry.lottery_draw.req.JobCreateReq;
import com.jerry.lottery_draw.req.JobQueryReq;
import com.jerry.lottery_draw.req.LotteryDrawReq;
import com.jerry.lottery_draw.resp.JobQueryResp;
import com.jerry.lottery_draw.resp.LotteryDrawResp;
import com.jerry.lottery_draw.resp.CommonResp;
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
     * @param req
     * @return
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
     * @param jobId
     * @return
     */
    @GetMapping("/{jobId}")
    public CommonResp list(@PathVariable Long jobId) {
        CommonResp<JobQueryResp> resp = new CommonResp<>();
        resp.setContent(jobService.query(jobId));
        return resp;
    }

    /**
     * 创建Job
     *
     * @param req
     * @return
     */
    @PostMapping("/")
    public CommonResp create(@RequestBody JobCreateReq req) {
        CommonResp<TJob> resp = new CommonResp<>();
        jobService.create(req);
        return resp;
    }

    /**
     * 删除Job
     *
     * @param jobId
     */
    @DeleteMapping("/{jobId}")
    public CommonResp delete(@PathVariable Long jobId) {
        CommonResp<TJob> resp = new CommonResp<>();
        jobService.delete(jobId);
        return resp;
    }

    /**
     * 根据JobId执行抽奖
     *
     * @param req
     * @return
     */
    @PostMapping("/draw-lottery")
    public CommonResp drawLottery(@RequestBody LotteryDrawReq req) {
        CommonResp<LotteryDrawResp> resp = new CommonResp<>();
        LotteryDrawResp lotteryDrawResp = jobService.drawLottery(req.getJobId());
        resp.setContent(lotteryDrawResp);
        return resp;
    }

    /**
     * 根据JobId获取获奖人员名单
     *
     * @param jobId
     * @return
     */
    @GetMapping("/list/result/{jobId}")
    public CommonResp listEmployeeId(@PathVariable Long jobId) {
        CommonResp<List<String>> resp = new CommonResp<>();
        resp.setContent(jobService.selectEmployeeIdsByJobId(jobId));
        return resp;
    }
}
