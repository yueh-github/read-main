package com.readmain.admin.controller;

import com.readmain.common.entity.TestUser;
import com.readmain.service.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by yuehao on 2017/8/27.
 */
@Controller
public class UserController {

    @Resource
    private TestService testService;

    @RequestMapping("/list")
    public List<TestUser> getUserList() throws Exception {
        return this.testService.getUserList();
    }

    @RequestMapping("/test/index")
    public ModelAndView indexPage() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/demo");
        return modelAndView;
    }
}
