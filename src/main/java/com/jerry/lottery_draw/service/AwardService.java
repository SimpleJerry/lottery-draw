package com.jerry.lottery_draw.service;

import cn.hutool.core.bean.BeanUtil;
import com.jerry.lottery_draw.domain.*;
import com.jerry.lottery_draw.exception.BusinessException;
import com.jerry.lottery_draw.exception.BusinessExceptionCode;
import com.jerry.lottery_draw.mapper.TAwardMapper;
import com.jerry.lottery_draw.mapper.THistoryMapper;
import com.jerry.lottery_draw.mapper.TJobMapper;
import com.jerry.lottery_draw.mapper.TEmployeeMapper;
import com.jerry.lottery_draw.req.AwardLotteryDrawReq;
import com.jerry.lottery_draw.resp.AwardLotteryDrawResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AwardService {

    private static final Logger LOG = LoggerFactory.getLogger(AwardService.class);

    @Resource
    private TAwardMapper tAwardMapper;

    @Resource
    private TEmployeeMapper tEmployeeMapper;

    @Resource
    private TJobMapper tJobMapper;

    @Resource
    private THistoryMapper tHistoryMapper;

    /**
     * 根据groupId和awardId查找一个奖品类型
     *
     * @param groupId
     * @param awardId
     * @return
     */
    public TAward selectAwardByGroupIdAndAwardId(String groupId, String awardId) {
        TAwardExample tAwardExample = new TAwardExample();
        tAwardExample.createCriteria().andGroupIdEqualTo(groupId).andAwardIdEqualTo(awardId);
        List<TAward> awardList = tAwardMapper.selectByExample(tAwardExample);
        if (CollectionUtils.isEmpty(awardList)) {
            return null;
        }
        else {
            return awardList.get(0);
        }
    }

    public AwardLotteryDrawResp drawLottery(AwardLotteryDrawReq req) {
        TAward award = selectAwardByGroupIdAndAwardId(req.getGroupId(), req.getGroupId());
        if (ObjectUtils.isEmpty(award)) {
            // 奖品不存在
            LOG.info("奖品不存在, groupId:{},awardId:{}", req.getGroupId(), req.getAwardId());
            throw new BusinessException(BusinessExceptionCode.AWARD_NOT_EXISTS);
        }
        else {
            AwardLotteryDrawResp awardLotteryDrawResp = new AwardLotteryDrawResp();
            BeanUtil.copyProperties(award, awardLotteryDrawResp);
            if (award.getRemainQuantity() <= 0) {
                // 当剩余数<=0时，set一个空列表
                awardLotteryDrawResp.setUserList(new ArrayList<TEmployee>());
            }
            else if (award.getRemainQuantity() < award.getOnceQuantity()) {
                // 当剩余数>0且<单次抽取数时
                // TODO
            }
            else {
                // 当剩余数>档次抽取数时
                // TODO
            }
            return awardLotteryDrawResp;
        }
    }
}


