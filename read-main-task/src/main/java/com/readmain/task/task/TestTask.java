package com.readmain.task.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by yuehao on 2017/9/12.
 */

@Component
public class TestTask {

    @Scheduled(cron = "*/5 * * * * ?")
    public void testTask()throws Exception{
        System.out.println("------------->>>>>>>测试任务");
    }
}
