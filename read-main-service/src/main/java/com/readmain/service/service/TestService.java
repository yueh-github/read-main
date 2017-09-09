package com.readmain.service.service;

import com.readmain.common.entity.TestUser;

import java.util.List;

/**
 * Created by yuehao on 2017/8/29.
 */
public interface TestService {

    List<TestUser> getUserList()throws Exception;
}
