//package com.readmain.admin.interceptor;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
///**
// * Created by yuehao on 2017/9/4.
// */
//@WebFilter(urlPatterns = "/*", filterName = "testFilter")
//public class TestFilter implements filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("TestFilter -------------------- > >>>>> :" + "init");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("TestFilter -------------------- > >>>>> :" + "doFilter");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("TestFilter -------------------- > >>>>> :" + "destroy");
//    }
//}
