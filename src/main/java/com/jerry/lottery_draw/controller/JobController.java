package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.mapper.TJobMapper;
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

    @Resource
    private TJobMapper tJobMapper;

    @GetMapping("/list/{groupId}")
    public CommonResp list(@PathVariable String groupId) {
        CommonResp<List<String>> resp = new CommonResp<>();
        List<String> jobIds = tJobMapper.getJobIds(groupId);
        resp.setContent(jobIds);
        return resp;
    }
}
