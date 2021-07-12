package com.jerry.lottery_draw.service;

import com.jerry.lottery_draw.domain.TAward;
import com.jerry.lottery_draw.domain.TAwardExample;
import com.jerry.lottery_draw.mapper.TAwardMapper;
import com.jerry.lottery_draw.resp.AwardQueryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AwardService {

    private static final Logger log = LoggerFactory.getLogger(AwardService.class);

    @Resource
    private TAwardMapper tAwardMapper;

    /**
     * 查询全部奖品
     *
     * @param groupId
     * @return
     */
    public List<AwardQueryRes> list(String groupId) {
        TAwardExample tAwardExample = new TAwardExample();
        tAwardExample.createCriteria().andGroupIdEqualTo(groupId);
        List<TAward> awardList = tAwardMapper.selectByExample(tAwardExample);
        // Bean转换
        List<AwardQueryRes> res = new ArrayList<>();
        for (TAward award : awardList) {
            AwardQueryRes resItem = new AwardQueryRes();
            BeanUtils.copyProperties(award, resItem);
            res.add(resItem);
        }
        return res;
    }

    /**
     * 查询单个奖品
     *
     * @param groupId
     * @param awardId
     * @return
     */
    public AwardQueryRes query(String groupId, String awardId) {
        TAwardExample tAwardExample = new TAwardExample();
        tAwardExample.createCriteria().andGroupIdEqualTo(groupId).andAwardIdEqualTo(awardId);
        List<TAward> awardList = tAwardMapper.selectByExample(tAwardExample);
        if (CollectionUtils.isEmpty(awardList)) {
            return null;
        }
        else {
            AwardQueryRes res = new AwardQueryRes();
            BeanUtils.copyProperties(awardList.get(0), res);
            return res;
        }
    }

}


