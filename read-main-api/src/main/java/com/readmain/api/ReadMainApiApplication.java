package com.readmain.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.readmain.api.controller","com.readmain.service","com.readmain.api.exception"})
@ServletComponentScan("com.readmain.api")
public class ReadMainApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReadMainApiApplication.class, args);
	}
}
