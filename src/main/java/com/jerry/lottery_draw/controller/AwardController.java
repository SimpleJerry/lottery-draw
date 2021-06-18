package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.domain.TAward;
import com.jerry.lottery_draw.req.AwardLotteryDrawReq;
import com.jerry.lottery_draw.resp.AwardLotteryDrawResp;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.AwardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/award")
public class AwardController {

    @Resource
    private AwardService awardService;

    @GetMapping("/show-all/{groupId}")
    public CommonResp showAll(@PathVariable("groupId") String groupId){
        CommonResp<List<TAward>> resp = new CommonResp<>();
        List<TAward> awardList = awardService.selectAwardsByGroupId(groupId);
        resp.setContent(awardList);
        return resp;
    }

    @GetMapping("/show-one/{awardId}")
    public CommonResp showOne(@PathVariable("awardId") String awardId){
        CommonResp<TAward> resp = new CommonResp<>();
        TAward award = awardService.selectAwardByAwardId(awardId);
        resp.setContent(award);
        return resp;
    }

    @PostMapping("/lottery-draw")
    public CommonResp drawLottery(@RequestBody AwardLotteryDrawReq awardLoginReq) {
        CommonResp<AwardLotteryDrawResp> resp = new CommonResp<>();
        AwardLotteryDrawResp awardLotteryDrawResp = awardService.drawLottery(awardLoginReq);
        resp.setContent(awardLotteryDrawResp);
        return resp;
    }
}
