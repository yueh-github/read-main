package com.readmain.admin.interceptor;



import com.readmain.common.utils.SessionAware;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "sessionFilter")
public class SessionAwareFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SessionAwareFilter -------------------- > >>>>> :" + "doFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SessionAware.setRequest(request);
        SessionAware.setResponse(response);
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SessionAwareFilter -------------------- > >>>>> :" + "init");
    }

    @Override
    public void destroy() {
        System.out.println("SessionAwareFilter -------------------- > >>>>> :" + "destroy");
    }
}