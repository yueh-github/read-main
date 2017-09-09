package com.readmain.service.service.impl;

import com.readmain.common.entity.TestUser;
import com.readmain.service.dao.TestUserDao;
import com.readmain.service.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuehao on 2017/8/29.
 */
@Service
public class TestServiceImpl implements TestService{

    @Resource
    private TestUserDao testUserDao;
    public List<TestUser> getUserList() throws Exception {
        return this.testUserDao.getTestList();
    }
}
