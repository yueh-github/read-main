package com.readmain.task.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by yuehao on 2017/9/15.
 */
@Component
public class TestInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化bean 会调用 --------- afterPropertiesSet");
    }


    public String init(String string) {
        return string + "-------- ---- -- --- - -- -- ---  --- -";
    }
}
