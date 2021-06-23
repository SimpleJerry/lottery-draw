package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.domain.TJob;
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
     * 根据groupId查询其下的所有Job信息
     *
     * @param groupId
     * @return
     */
    @GetMapping("/list/{groupId}")
    public CommonResp listJob(@PathVariable String groupId) {
        CommonResp<List<TJob>> resp = new CommonResp<>();
        resp.setContent(jobService.selectJobByGroupId(groupId));
        return resp;
    }

    /**
     * 在某企业下新增一个Job
     *
     * @param groupId
     * @return
     */
    @PutMapping("/add/{groupId}")
    public CommonResp add(@PathVariable String groupId) {
        CommonResp<TJob> resp = new CommonResp<>();
        resp.setContent(jobService.createJob(groupId));
        return resp;
    }

    /**
     * 通过jobId删除Job
     *
     * @param jobId
     */
    @DeleteMapping("/delete/{jobId}")
    public CommonResp add(@PathVariable Long jobId) {
        CommonResp<TJob> resp = new CommonResp<>();
        resp.setContent(jobService.deleteJob(jobId));
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
