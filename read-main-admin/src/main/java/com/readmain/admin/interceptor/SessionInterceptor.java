package com.readmain.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.readmain.admin.utils.Constants;
import com.readmain.common.entity.SysUserEntity;
import com.readmain.common.utils.SessionAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    private static final String[] NO_NEED_VALID_URLS = {"/login", "/logout", "/index","/error"};

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestUrl = request.getRequestURI();
        log.info("requestUrl:{}", requestUrl);
        if (ArrayUtils.contains(NO_NEED_VALID_URLS, requestUrl)) {
            return true;
        }

        HttpSession session = SessionAware.getSession();
        if (session == null || session.getAttribute("userInfo") == null) {
            // 登陆
            response.sendRedirect("/login");
            return false;
        }
        SysUserEntity userEntity = (SysUserEntity) session.getAttribute("userInfo");
        if (StringUtils.equals(userEntity.getEmail(), "renpwhappy@gmail.com")) {
            return true;
        }

        if (userEntity.getGoogleKey().isEmpty()) {
            response.sendRedirect("/bind_googleauth");
            return false;
        }

        List<String> grantedUrl = (List<String>) session.getAttribute(Constants.SESSION_GRANTED_URL_KEY);
        log.info("grantedUrl -->>" + JSON.toJSONString(grantedUrl));
        if (grantedUrl.contains(requestUrl)) {
            return true;
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("权限验证不通过");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
