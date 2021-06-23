package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.domain.TAward;
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
}
