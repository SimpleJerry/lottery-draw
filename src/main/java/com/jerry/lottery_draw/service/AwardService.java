package com.jerry.lottery_draw.service;

import com.jerry.lottery_draw.domain.TAward;
import com.jerry.lottery_draw.domain.TAwardExample;
import com.jerry.lottery_draw.mapper.TAwardMapper;
import com.jerry.lottery_draw.mapper.TEmployeeMapper;
import com.jerry.lottery_draw.mapper.THistoryMapper;
import com.jerry.lottery_draw.mapper.TJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AwardService {

    private static final Logger LOG = LoggerFactory.getLogger(AwardService.class);

    @Resource
    private TAwardMapper tAwardMapper;

    @Resource
    private JobService jobService;

    @Resource
    private TEmployeeMapper tEmployeeMapper;

    @Resource
    private TJobMapper tJobMapper;

    @Resource
    private THistoryMapper tHistoryMapper;

    /**
     * 根据awardId查询某奖品种类信息
     *
     * @param awardId
     * @return
     */
    public TAward selectAwardByAwardId(String awardId) {
        TAwardExample tAwardExample = new TAwardExample();
        tAwardExample.createCriteria().andAwardIdEqualTo(awardId);
        List<TAward> awardList = tAwardMapper.selectByExample(tAwardExample);
        if (CollectionUtils.isEmpty(awardList)) {
            return null;
        }
        else {
            return awardList.get(0);
        }
    }

    /**
     * 根据groupId查询其下所有奖品种类
     *
     * @param groupId
     * @return
     */
    public List<TAward> selectAwardsByGroupId(String groupId) {
        TAwardExample tAwardExample = new TAwardExample();
        tAwardExample.createCriteria().andGroupIdEqualTo(groupId);
        List<TAward> awardList = tAwardMapper.selectByExample(tAwardExample);
        return awardList;
    }

}


