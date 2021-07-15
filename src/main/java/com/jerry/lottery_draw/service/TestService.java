package com.jerry.lottery_draw.service;

import com.jerry.lottery_draw.domain.Test;
import com.jerry.lottery_draw.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.selectList(null);
    }
}


