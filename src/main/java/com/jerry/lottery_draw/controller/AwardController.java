package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.req.AwardAddReq;
import com.jerry.lottery_draw.req.AwardQueryReq;
import com.jerry.lottery_draw.req.AwardUpdateReq;
import com.jerry.lottery_draw.resp.AwardQueryResp;
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

    /**
     * 根据条件查询奖品
     *
     * @param req AwardQueryReq
     * @return List<AwardQueryResp>
     */
    @GetMapping("/{groupId}")
    public CommonResp<List<AwardQueryResp>> list(@RequestBody AwardQueryReq req) {
        CommonResp<List<AwardQueryResp>> resp = new CommonResp<>();
        List<AwardQueryResp> res = awardService.list(req);
        resp.setContent(res);
        return resp;
    }

    /**
     * 根据Id查询单个奖品
     *
     * @param awardId String
     * @return AwardQueryResp
     */
    @GetMapping("/{awardId}")
    public CommonResp<AwardQueryResp> query(@PathVariable String awardId) {
        CommonResp<AwardQueryResp> resp = new CommonResp<>();
        AwardQueryResp res = awardService.query(awardId);
        resp.setContent(res);
        return resp;
    }

    /**
     * 创建奖品
     *
     * @param req AwardAddReq
     * @return null
     */
    @PostMapping("/")
    public CommonResp<Object> add(@RequestBody AwardAddReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        awardService.add(req);
        return resp;
    }

    /**
     * 删除奖品
     *
     * @param awardId String
     * @return null
     */
    @DeleteMapping("/{awardId}")
    public CommonResp<Object> delete(@PathVariable String awardId) {
        CommonResp<Object> resp = new CommonResp<>();
        awardService.delete(awardId);
        return resp;
    }

    /**
     * 更新奖品
     *
     * @param awardId String
     * @param req     AwardUpdateReq
     * @return
     */
    @PutMapping("/{awardId}")
    public CommonResp<Object> update(@PathVariable String awardId, @RequestBody AwardUpdateReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        awardService.update(awardId, req);
        return resp;
    }
}
