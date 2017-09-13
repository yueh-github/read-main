package com.readmain.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yuehao on 2017/9/13.
 */
@Configuration
public class Config {

    @Bean
    public BlockingQueue<String> getQueue() {
        return new ArrayBlockingQueue(10000);
    }

    @Bean
    public BlockingQueue<Integer> getBlockQueue(){
        return new ArrayBlockingQueue(10000);
    }
}
