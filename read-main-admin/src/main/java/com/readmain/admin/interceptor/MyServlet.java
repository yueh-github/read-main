package com.readmain.admin.interceptor;

import com.opensymphony.module.sitemesh.freemarker.FreemarkerDecoratorServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created by yuehao on 2017/9/1.
 */
@WebServlet(urlPatterns = "*.html",description = "sitemesh-freemarker")
public class MyServlet extends FreemarkerDecoratorServlet {




}
