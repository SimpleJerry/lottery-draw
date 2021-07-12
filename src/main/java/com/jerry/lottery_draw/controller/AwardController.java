package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.resp.AwardQueryResp;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.AwardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/award")
public class AwardController {

    @Resource
    private AwardService awardService;

    /**
     * 查询全部奖品
     *
     * @param groupId
     * @return
     */
    @GetMapping("/{groupId}")
    public CommonResp list(@PathVariable String groupId) {
        CommonResp<List<AwardQueryResp>> resp = new CommonResp<>();
        List<AwardQueryResp> awardList = awardService.list(groupId);
        resp.setContent(awardList);
        return resp;
    }

    /**
     * 查询单个奖品
     *
     * @param groupId
     * @param awardId
     * @return
     */
    @GetMapping("/{groupId}/{awardId}")
    public CommonResp<AwardQueryResp> query(@PathVariable String groupId, @PathVariable String awardId) {
        CommonResp<AwardQueryResp> resp = new CommonResp<>();
        AwardQueryResp res = awardService.query(groupId, awardId);
        resp.setContent(res);
        return resp;
    }
}
