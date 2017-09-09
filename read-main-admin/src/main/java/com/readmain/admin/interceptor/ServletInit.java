package com.readmain.admin.interceptor;

import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by yuehao on 2017/9/1.
 */

@Configuration
public class ServletInit extends SpringBootServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("sitemesh.configfile", "classpath:/sitemesh.xml");
        super.onStartup(servletContext);
    }
}
