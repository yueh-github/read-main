package com.readmain.service;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * service 对应的配置文件
 */
@Configuration
@ComponentScan(basePackages = "com.readmain.service")
@MapperScan(basePackages = "com.readmain.service.dao", annotationClass = Mapper.class)
public class ServiceAutoConfiguration {

}
