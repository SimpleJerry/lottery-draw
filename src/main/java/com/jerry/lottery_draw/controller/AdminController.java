package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.req.AdminCreateReq;
import com.jerry.lottery_draw.req.AdminLoginReq;
import com.jerry.lottery_draw.req.AdminUpdateReq;
import com.jerry.lottery_draw.resp.AdminLoginResp;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.AdminService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 创建帐号
     *
     * @param req
     * @return
     */
    @PostMapping("/")
    public CommonResp<Object> create(@RequestBody AdminCreateReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<Object> resp = new CommonResp<>();
        adminService.create(req);
        return resp;
    }

    /**
     * 删除帐号
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{account}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        CommonResp<Object> resp = new CommonResp<>();
        adminService.delete(id);
        return resp;
    }

    /**
     * 更新帐号
     *
     * @param account
     * @param req
     * @return
     */
    @PutMapping("/{account}")
    public CommonResp<Object> update(@PathVariable String account, @RequestBody AdminUpdateReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        adminService.update(account, req);
        return resp;
    }

    /**
     * 登录验证
     *
     * @param adminLoginReq
     * @return
     */
    @PostMapping("/login")
    public CommonResp<AdminLoginResp> login(@RequestBody AdminLoginReq adminLoginReq) {
        CommonResp<AdminLoginResp> resp = new CommonResp<>();
        AdminLoginResp adminLoginResp = adminService.login(adminLoginReq);
        resp.setContent(adminLoginResp);
        return resp;
    }
}
