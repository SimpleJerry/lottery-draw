package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.req.AwardAddReq;
import com.jerry.lottery_draw.req.AwardQueryReq;
import com.jerry.lottery_draw.req.AwardUpdateReq;
import com.jerry.lottery_draw.req.AwardsResetReq;
import com.jerry.lottery_draw.resp.AwardQueryResp;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.AwardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/award")
public class AwardController {

    @Resource
    private AwardService awardService;

    /**
     * 查询全部奖品
     *
     * @param req AwardQueryReq
     * @return List<AwardQueryResp>
     */
    @ApiOperation(value = "查询全部奖品", notes = "", response = CommonResp.class)
    @PostMapping("/")
    public CommonResp<List<AwardQueryResp>> list(@RequestBody @Valid AwardQueryReq req) {
        CommonResp<List<AwardQueryResp>> resp = new CommonResp<>();
        List<AwardQueryResp> res = awardService.list(req);
        resp.setContent(res);
        return resp;
    }

    /**
     * 查询单个奖品
     *
     * @param awardId String
     * @return AwardQueryResp
     */
    @ApiOperation(value = "查询单个奖品", notes = "", response = CommonResp.class)
    @GetMapping("/{awardId}")
    public CommonResp<AwardQueryResp> query(@PathVariable @Valid String awardId) {
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
    @ApiOperation(value = "创建奖品", notes = "", response = CommonResp.class)
    @PutMapping("/")
    public CommonResp<Object> add(@RequestBody @Valid AwardAddReq req) {
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
    @ApiOperation(value = "删除奖品", notes = "", response = CommonResp.class)
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
     * @return null
     */
    @ApiOperation(value = "更新奖品", notes = "", response = CommonResp.class)
    @PutMapping("/{awardId}")
    public CommonResp<Object> update(@PathVariable String awardId, @RequestBody @Valid AwardUpdateReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        awardService.update(awardId, req);
        return resp;
    }

    /**
     * 重置奖品状态
     *
     * @param awardId String
     * @return null
     */
    @ApiOperation(value = "重置奖品状态(单个)", notes = "", response = CommonResp.class)
    @PutMapping("/{awardId}/reset")
    public CommonResp<Object> reset(@PathVariable String awardId) {
        CommonResp<Object> resp = new CommonResp<>();
        awardService.reset(awardId);
        return resp;
    }

    /**
     * 重置奖品状态
     *
     * @param req AwardsResetReq
     * @return null
     */
    @ApiOperation(value = "重置奖品状态(批量)", notes = "", response = CommonResp.class)
    @PutMapping("/reset")
    public CommonResp<Object> reset(@RequestBody @Valid AwardsResetReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        awardService.reset(req);
        return resp;
    }
}
