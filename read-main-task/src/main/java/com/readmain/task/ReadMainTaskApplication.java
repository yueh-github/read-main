package com.readmain.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication(scanBasePackages = {"com.readmain.task", "com.readmain.service"})
@EnableAutoConfiguration
@EnableScheduling
public class ReadMainTaskApplication {

    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        SpringApplication.run(ReadMainTaskApplication.class, args);
//        new Thread(new ProductThread()).start();

//        for (int i = 0; i < 5; i++) {
//            threadPoolExecutor.execute(new ReadThread());
//        }
    }
}
