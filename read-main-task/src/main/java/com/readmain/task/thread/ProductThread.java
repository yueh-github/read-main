package com.readmain.task.thread;

import com.readmain.task.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

/**
 * Created by yuehao on 2017/9/13.
 */
@Slf4j
public class ProductThread implements Runnable {

    private static final String string = "---- ------ ---- ---- ----- ----- ------ ------ ----- do do do do do";

    private BlockingQueue blockingQueue;

    public ProductThread() {
        this.blockingQueue = ApplicationContextProvider.getBean("getQueue", BlockingQueue.class);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10);
                blockingQueue.put(string);
//                log.info("生产者存入数据  ************************************* 队列长度" + blockingQueue.size());
            }
        } catch (Exception ex) {

        }
    }
}
