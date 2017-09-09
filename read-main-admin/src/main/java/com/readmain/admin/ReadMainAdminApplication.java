package com.readmain.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"com.readmain.admin", "com.readmain.service"})
@ServletComponentScan("com.readmain.admin.interceptor")
public class ReadMainAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadMainAdminApplication.class, args);
    }
}
