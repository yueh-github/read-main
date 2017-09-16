//package com.readmain.task.thread;
//
//import com.readmain.task.ApplicationContextProvider;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.annotation.Resource;
//import java.util.concurrent.BlockingQueue;
//
///**
// * Created by yuehao on 2017/9/13.
// */
//@Slf4j
//public class ReadThread implements Runnable {
//
//
//    @Resource
//    private BlockingQueue<String> blockingQueue;
//
//    public ReadThread() {
//        blockingQueue = ApplicationContextProvider.getBean("getQueue", BlockingQueue.class);
//    }
//    //    @Resource
////    private TestService testService;
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                String string = this.blockingQueue.take();
//                log.info(Thread.currentThread().getName() + " 消费者线程获取到数据 :" + string + " 当前队列元素数量 :" + blockingQueue.size());
////            TestUser testUser = new Gson().fromJson(string, TestUser.class);
////            testUser.setAge(blockingQueue.size());
////            testUser.setName(testUser.getName() + "/" + blockingQueue.size());
////            testService.insertTestUser(testUser);
//            }
//        } catch (Exception ex) {
//            log.error("消费者线程处理失败 {}", ex);
//        }
//    }
//}
