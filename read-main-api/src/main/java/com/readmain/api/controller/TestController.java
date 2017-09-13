package com.readmain.api.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.readmain.common.entity.TestUser;
import com.readmain.service.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuehao on 2017/9/12.
 */
@Slf4j
@RestController
public class TestController {


    @Resource
    private TestService testService;

    @RequestMapping("/index")
    public String getIndexData() throws Exception {
        return "this is index page";
    }

    @RequestMapping("/list")
    public String getListData() throws Exception {

        List<TestUser> testUserList = this.testService.getUserList();
//        log.info("获取到的json数据{}", new Gson().toJson(testUserList));
        return new Gson().toJson(testUserList);
    }
}
