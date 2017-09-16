//package com.readmain.api.Listener;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.ServletRequestAttributeEvent;
//import javax.servlet.ServletRequestAttributeListener;
//import javax.servlet.annotation.WebListener;
//
///**
// * Created by yuehao on 2017/9/16.
// */
//
//@Slf4j
//@WebListener
//public class RequestListener implements ServletRequestAttributeListener {
//
//
//    @Override
//    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//        log.info("- --- -------- --- --创建request对象 -- " +new Gson().toJson(servletRequestAttributeEvent));
//    }
//
//    @Override
//    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//
//    }
//
//    @Override
//    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//
//    }
//}
