package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.req.AwardLotteryDrawReq;
import com.jerry.lottery_draw.resp.AwardLotteryDrawResp;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.AwardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/award")
public class AwardController {

    @Resource
    private AwardService awardService;

    @PostMapping("/lottery-draw")
    public CommonResp drawLottery(@RequestBody AwardLotteryDrawReq awardLoginReq) {
        CommonResp<AwardLotteryDrawResp> resp = new CommonResp<>();
        AwardLotteryDrawResp awardLotteryDrawResp = awardService.drawLottery(awardLoginReq);
        resp.setContent(awardLotteryDrawResp);
        return resp;
    }
}
