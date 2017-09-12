package com.readmain.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.readmain.task","com.readmain.service"})
@EnableAutoConfiguration
@EnableScheduling
public class ReadMainTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadMainTaskApplication.class, args);
    }
}
