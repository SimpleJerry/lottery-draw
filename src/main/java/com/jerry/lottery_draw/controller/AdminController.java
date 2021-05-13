package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.req.AdminLoginReq;
import com.jerry.lottery_draw.resp.AdminLoginResp;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public CommonResp selectAdminsByGroupId(@RequestBody AdminLoginReq adminLoginReq) {
        CommonResp<AdminLoginResp> resp = new CommonResp<>();
        AdminLoginResp adminLoginResp = adminService.login(adminLoginReq);
        resp.setContent(adminLoginResp);
        return resp;
    }
}
