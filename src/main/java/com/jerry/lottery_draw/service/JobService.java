package com.jerry.lottery_draw.service;

import com.jerry.lottery_draw.domain.TJob;
import com.jerry.lottery_draw.domain.TJobExample;
import com.jerry.lottery_draw.mapper.TJobMapper;
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

    public List<String> selectJobIdsByGroupId(String groupId) {
        return tJobMapper.getJobIds(groupId);
    }

    public List<String> selectEmployeeIdsByJobId(String jobId) {
        TJobExample tJobExample = new TJobExample();
        tJobExample.createCriteria().andJobIdEqualTo(jobId);
        List<TJob> tJobs = tJobMapper.selectByExample(tJobExample);
        List<String> employeeIds = new ArrayList<>();
        for (TJob tjob : tJobs) {
            employeeIds.add(tjob.getEmployeeId());
        }
        return employeeIds;
    }

}


