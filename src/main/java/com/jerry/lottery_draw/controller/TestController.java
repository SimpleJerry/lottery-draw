package com.jerry.lottery_draw.controller;

import com.jerry.lottery_draw.domain.Test;
import com.jerry.lottery_draw.resp.CommonResp;
import com.jerry.lottery_draw.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    /**
     * GET, POST, PUT, DELETE
     * <p>
     * /user?id=1
     * /user/1
     *
     * @return
     */
    // @PostMapping
    // @PutMapping
    // @DeleteMapping
    // @RequestMapping(value = "/user/1", method = RequestMethod.GET)
    // @RequestMapping(value = "/user/1", method = RequestMethod.DELETE)
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/test/list")
    public CommonResp list() {
        CommonResp<List<Test>> resp = new CommonResp<>();
        List<Test> list = testService.list();
        resp.setContent(list);
        return resp;
    }
}
