package com.readmain.task.task;

import com.readmain.service.service.TestService;
import com.readmain.task.demo.TestInitializingBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yuehao on 2017/9/12.
 */

@Slf4j
@Component
public class TestTask {

    @Resource
    private TestService testService;


    @Autowired
    private BlockingQueue<String> queue;

    @Resource
    private BlockingQueue<Integer> getBlockQueue;

    @Resource
    private TestInitializingBean testInitializingBean;

//    @Scheduled(cron = "*/1 * * * * ?")
//    public void testTask() throws Exception {
//        String string = "{\"id\":1,\"name\":\"岳浩\",\"age\":29},{\"id\":2,\"name\":\"李爱云\",\"age\":28}";
//        List<TestUser> ListtestUserList = this.testService.getUserList();
//        queue.put(string);
//        log.info("放入队列数据 ---" + queue.size());
//    }


    @Scheduled(cron = "*/5 * * * * ?")
    public void testTaskInit() throws Exception {
        String string = "{\"id\":1,\"name\":\"岳浩\",\"age\":29},{\"id\":2,\"name\":\"李爱云\",\"age\":28}";
//        log.info("放入队列数据 ---" + testInitializingBean.init(string));
    }


    @Scheduled(cron = "0 */1 * * * ?")
    public void testTask() throws Exception {
        String string = "{\"id\":1,\"name\":\"岳浩\",\"age\":29},{\"id\":2,\"name\":\"李爱云\",\"age\":28}";
//        List<TestUser> ListtestUserList = this.testService.getUserList();
        queue.put(string);
//        log.info("放入队列数据 ---" + queue.size());
    }
}
