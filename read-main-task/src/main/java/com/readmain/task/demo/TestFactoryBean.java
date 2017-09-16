package com.readmain.task.demo;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by yuehao on 2017/9/15.
 */
public class TestFactoryBean implements FactoryBean{

    @Override
    public Object getObject() throws Exception {
        return new TestFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestFactoryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
