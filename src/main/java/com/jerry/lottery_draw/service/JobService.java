package com.jerry.lottery_draw.service;

import cn.hutool.core.bean.BeanUtil;
import com.jerry.lottery_draw.domain.TEmployee;
import com.jerry.lottery_draw.domain.TJob;
import com.jerry.lottery_draw.domain.TJobExample;
import com.jerry.lottery_draw.exception.BusinessException;
import com.jerry.lottery_draw.exception.BusinessExceptionCode;
import com.jerry.lottery_draw.mapper.TEmployeeMapper;
import com.jerry.lottery_draw.mapper.THistoryMapper;
import com.jerry.lottery_draw.mapper.TJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private static final Logger LOG = LoggerFactory.getLogger(JobService.class);

    @Resource
    private TJobMapper tJobMapper;

    public List<String> list(String groupId) {
        return tJobMapper.getJobIds(groupId);
    }

}


