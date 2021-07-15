package com.jerry.lottery_draw.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jerry.lottery_draw.domain.TAward;
import com.jerry.lottery_draw.exception.BusinessException;
import com.jerry.lottery_draw.exception.BusinessExceptionCode;
import com.jerry.lottery_draw.mapper.TAwardMapper;
import com.jerry.lottery_draw.req.AwardAddReq;
import com.jerry.lottery_draw.req.AwardQueryReq;
import com.jerry.lottery_draw.req.AwardUpdateReq;
import com.jerry.lottery_draw.resp.AwardQueryResp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AwardService {

    private static final Logger log = LoggerFactory.getLogger(AwardService.class);

    @Resource
    private TAwardMapper tAwardMapper;

    /**
     * 根据奖品Id查询对应的奖品
     *
     * @param awardId 奖品Id
     * @return TAward
     */
    public TAward selectAwardById(String awardId) {
        LambdaQueryWrapper<TAward> sqlWhereWrapper = new LambdaQueryWrapper<TAward>()
                .eq(StringUtils.isNotBlank(awardId), TAward::getAwardId, awardId);
        List<TAward> awardList = tAwardMapper.selectList(sqlWhereWrapper);
        if (CollectionUtils.isEmpty(awardList)) {
            // 未找到
            return null;
        }
        else {
            return awardList.get(0);
        }
    }

    /**
     * 根据条件查询奖品
     *
     * @param req AwardQueryReq
     * @return List<AwardQueryResp>
     */
    public List<AwardQueryResp> list(AwardQueryReq req) {
        LambdaQueryWrapper<TAward> sqlWhereWrapper = new LambdaQueryWrapper<TAward>()
                .eq(StringUtils.isNotBlank(req.getGroupId()), TAward::getGroupId, req.getGroupId())
                .eq(StringUtils.isNotBlank(req.getAwardId()), TAward::getAwardId, req.getAwardId())
                .eq(StringUtils.isNotBlank(req.getAwardName()), TAward::getAwardName, req.getAwardName());
        List<TAward> awardList = tAwardMapper.selectList(sqlWhereWrapper);
        // Bean转换
        List<AwardQueryResp> res = new ArrayList<>();
        for (TAward award : awardList) {
            AwardQueryResp resItem = new AwardQueryResp();
            BeanUtils.copyProperties(award, resItem);
            res.add(resItem);
        }
        return res;
    }

    /**
     * 查询单个奖品
     *
     * @param awardId 奖品Id
     * @return AwardQueryResp
     */
    public AwardQueryResp query(String awardId) {
        TAward tAward = selectAwardById(awardId);
        if (ObjectUtils.isEmpty(tAward)) {
            return null;
        }
        else {
            AwardQueryResp res = new AwardQueryResp();
            BeanUtils.copyProperties(tAward, res);
            return res;
        }
    }

    /**
     * 创建奖品
     *
     * @param req AwardAddReq
     */
    public void add(AwardAddReq req) {
        // 创建待插入对象
        TAward tAward = new TAward();
        BeanUtils.copyProperties(req, tAward);
        tAward.setAwardId(new Snowflake(1, 1).nextIdStr());
        // 执行创建
        tAwardMapper.insert(tAward);
    }

    /**
     * 删除奖品
     *
     * @param awardId 奖品Id
     */
    public void delete(String awardId) {
        TAward tAward = selectAwardById(awardId);
        if (ObjectUtil.isEmpty(tAward)) {
            log.info("奖品不存在：{}", awardId);
            throw new BusinessException(BusinessExceptionCode.AWARD_NOT_EXISTS);
        }
        // 执行删除
        tAwardMapper.deleteById(tAward.getId());
    }

    /**
     * 更新奖品
     *
     * @param awardId String
     * @param req     AwardUpdateReq
     */
    public void update(String awardId, AwardUpdateReq req) {
        TAward tAward = selectAwardById(awardId);
        if (ObjectUtil.isEmpty(tAward)) {
            log.info("奖品不存在：{}", awardId);
            throw new BusinessException(BusinessExceptionCode.AWARD_NOT_EXISTS);
        }
        BeanUtils.copyProperties(req, tAward);
        tAward.setUpdatedAt(new Date());
        // 执行更新
        tAwardMapper.updateById(tAward);
    }

    /**
     * 重置奖品状态
     *
     * @param awardId String
     */
    public void reset(String awardId) {
        TAward tAward = selectAwardById(awardId);
        if (ObjectUtil.isEmpty(tAward)) {
            log.info("奖品不存在：{}", awardId);
            throw new BusinessException(BusinessExceptionCode.AWARD_NOT_EXISTS);
        }
        tAward.setRemainQuantity(tAward.getTotalQuantity());
        // 执行更新
        tAwardMapper.updateById(tAward);
    }

}


