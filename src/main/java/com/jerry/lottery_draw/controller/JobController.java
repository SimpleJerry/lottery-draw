package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    @GetMapping("/list/jobId/{groupId}")
    public CommonResp listJobId(@PathVariable String groupId) {
        CommonResp<List<String>> resp = new CommonResp<>();
        resp.setContent(jobService.selectJobIdsByGroupId(groupId));
        return resp;
    }

    @GetMapping("/list/employeeId/{jobId}")
    public CommonResp listEmployeeId(@PathVariable String jobId) {
        CommonResp<List<String>> resp = new CommonResp<>();
        resp.setContent(jobService.selectEmployeeIdsByJobId(jobId));
        return resp;
    }
}
