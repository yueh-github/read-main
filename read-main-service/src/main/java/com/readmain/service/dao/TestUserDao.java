package com.readmain.service.dao;


import com.readmain.common.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestUserDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestUser record);

    List<TestUser> getTestList()throws Exception;
}