package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.req.AdminCreateReq;
import com.jerry.lottery_draw.req.AdminLoginReq;
import com.jerry.lottery_draw.req.AdminUpdateReq;
import com.jerry.lottery_draw.resp.AdminLoginResp;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 创建帐号
     *
     * @param req AdminCreateReq
     * @return CommonResp<Object>
     */
    @ApiOperation(value = "创建帐号", notes = "", response = CommonResp.class)
    @PostMapping("/")
    public CommonResp<Object> create(@RequestBody @Valid AdminCreateReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        // 密码进行MD5加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        adminService.create(req);
        return resp;
    }

    /**
     * 删除帐号
     *
     * @param account String
     * @return CommonResp<Object>
     */
    @ApiOperation(value = "删除帐号", notes = "", response = CommonResp.class)
    @DeleteMapping("/{account}")
    public CommonResp<Object> delete(@PathVariable String account) {
        CommonResp<Object> resp = new CommonResp<>();
        adminService.delete(account);
        return resp;
    }

    /**
     * 更新帐号
     *
     * @param account String
     * @param req     AdminUpdateReq
     * @return CommonResp<Object>
     */
    @ApiOperation(value = "更新帐号", notes = "", response = CommonResp.class)
    @PutMapping("/{account}")
    public CommonResp<Object> update(@PathVariable String account, @RequestBody @Valid AdminUpdateReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        adminService.update(account, req);
        return resp;
    }

    /**
     * 登录验证
     *
     * @param adminLoginReq AdminLoginReq
     * @return CommonResp<AdminLoginResp>
     */
    @ApiOperation(value = "登录验证", notes = "", response = CommonResp.class)
    @PostMapping("/login")
    public CommonResp<AdminLoginResp> login(@RequestBody @Valid AdminLoginReq adminLoginReq) {
        CommonResp<AdminLoginResp> resp = new CommonResp<>();
        AdminLoginResp adminLoginResp = adminService.login(adminLoginReq);
        resp.setContent(adminLoginResp);
        return resp;
    }
}
