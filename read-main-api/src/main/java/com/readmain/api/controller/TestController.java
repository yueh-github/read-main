package com.readmain.api.controller;

import com.google.gson.Gson;
import com.readmain.api.exception.EReadException;
import com.readmain.api.exception.ReadException;
import com.readmain.common.entity.TestUser;
import com.readmain.service.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String getListData(HttpServletRequest request) throws Exception {

        List<TestUser> testUserList = this.testService.getUserList();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("name", "岳浩");
//        log.info("获取到的json数据{}", new Gson().toJson(testUserList));
        return new Gson().toJson(testUserList);
    }

    @RequestMapping("/exception")
    public void testException() throws Exception {
        throw new Exception("网络异常");
    }

    @RequestMapping("/read_exception")
    public void testReadExcption() throws Exception {
        throw new ReadException("网络错误", 100002);
    }

    @RequestMapping("/test_read_exception")
    public void testReadExceptionApi()throws Exception{
        throw EReadException.SYS_WARN.buildReadException();
    }
}
