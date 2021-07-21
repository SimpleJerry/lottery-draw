package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.domain.TJobResult;
import com.jerry.lottery_draw.req.JobCreateReq;
import com.jerry.lottery_draw.req.JobQueryReq;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.resp.JobQueryResp;
import com.jerry.lottery_draw.resp.JobDoResp;
import com.jerry.lottery_draw.service.JobService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    /**
     * 查询全部Job
     *
     * @param req JobQueryReq
     * @return List<JobQueryResp>
     */
    @ApiOperation(value = "查询全部Job", notes = "", response = CommonResp.class)
    @PostMapping("/")
    public CommonResp<List<JobQueryResp>> list(@RequestBody @Valid JobQueryReq req) {
        CommonResp<List<JobQueryResp>> resp = new CommonResp<>();
        resp.setContent(jobService.list(req));
        return resp;
    }

    /**
     * 查询单个Job
     *
     * @param jobId Long
     * @return JobQueryResp
     */
    @ApiOperation(value = "查询单个Job", notes = "", response = CommonResp.class)
    @GetMapping("/{jobId}")
    public CommonResp<JobQueryResp> query(@PathVariable @Valid Long jobId) {
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
    @ApiOperation(value = "创建Job", notes = "", response = CommonResp.class)
    @PutMapping("/")
    public CommonResp<Object> create(@RequestBody @Valid JobCreateReq req) {
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
    @ApiOperation(value = "删除Job", notes = "", response = CommonResp.class)
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
    @ApiOperation(value = "进行抽奖事务", notes = "", response = CommonResp.class)
    @PostMapping("/{jobId}/do")
    public CommonResp<JobDoResp> doJob(@PathVariable Long jobId) {
        CommonResp<JobDoResp> resp = new CommonResp<>();
        JobDoResp jobDoResp = jobService.doJob(jobId);
        resp.setContent(jobDoResp);
        return resp;
    }

    /**
     * 查询抽奖结果
     *
     * @param jobId Long
     * @return List<TJobResult>
     */
    @ApiOperation(value = "查询抽奖结果", notes = "", response = CommonResp.class)
    @GetMapping("/{jobId}/result")
    public CommonResp<List<TJobResult>> queryJobResult(@PathVariable Long jobId) {
        CommonResp<List<TJobResult>> resp = new CommonResp<>();
        resp.setContent(jobService.queryJobResult(jobId));
        return resp;
    }
}
