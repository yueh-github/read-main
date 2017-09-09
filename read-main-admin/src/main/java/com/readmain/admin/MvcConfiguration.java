package com.readmain.admin;

import com.opensymphony.module.sitemesh.freemarker.FreemarkerDecoratorServlet;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import com.readmain.admin.interceptor.SessionInterceptor;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Map;

/**
 * Created by yuehao on 2017/8/29.
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new SiteMeshFilter());
        filter.setName("sitemesh");
        filter.addUrlPatterns("/*");
        return filter;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        Map<String, String> map = new HashedMap();
        map.put("TemplatePath", "/WEB-INF/views/");
        map.put("default_encoding", "UTF-8");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new FreemarkerDecoratorServlet());
        servletRegistrationBean.setName("sitemesh-freemarker");
        servletRegistrationBean.setInitParameters(map);
        servletRegistrationBean.setLoadOnStartup(2);
        servletRegistrationBean.addUrlMappings("*.html");
        return servletRegistrationBean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new SessionInterceptor())
//                .excludePathPatterns(
//                        "/index"
//                );
        super.addInterceptors(registry);
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
