package com.readmain.admin.controller;

import com.alibaba.fastjson.JSON;
import com.readmain.admin.utils.AdminUtils;
import com.readmain.admin.utils.CommonUtils;
import com.readmain.admin.utils.Constants;
import com.readmain.admin.utils.QRCodeUtils;
import com.readmain.common.entity.SysResourceEntity;
import com.readmain.common.entity.SysUserEntity;
import com.readmain.common.entity.SysUserMenuEntity;
import com.readmain.common.exception.CustomerException;
import com.readmain.common.utils.SessionAware;
import com.readmain.service.service.IAdminService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class LoginController implements InitializingBean {

    @Resource
    private IAdminService adminService;

    @Resource
    private AdminUtils adminUtils;

    @Value("${isDev}")
    private String isDev;

    @Value("${domainUrl}")
    private String domainUrl;

    @Value("${backUrl}")
    private String backUrl;

    @RequestMapping(value = {"/", "/index", "/welcome"}, method = RequestMethod.GET)
    public String index() {
        HttpSession session = SessionAware.getSession();
        if (!(session != null && session.getAttribute("userInfo") != null)) {
            return "redirect:/login";
        }
        return "welcome";
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String gotoLogin(HttpServletRequest servletRequest) {
        log.info("idDev:{}", isDev);
        log.info("sitemesh:{}", servletRequest.getServletContext().getInitParameter("sitemesh.configfile"));
        HttpSession session = SessionAware.getSession();
        if (session != null && session.getAttribute("userInfo") != null) {
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model, @RequestParam String username, @RequestParam String password, @RequestParam Integer googlePwd) throws Exception {
        try {
            SysUserEntity userEntity = adminService.doLogin(username, password, null);
            boolean bindGoogleKey = true;
            if (!Boolean.valueOf(isDev) && StringUtils.isNotEmpty(userEntity.getGoogleKey())) {
                bindGoogleKey = false;
                GoogleAuthenticator gAuth = new GoogleAuthenticator();
                boolean isValid = gAuth.authorize(userEntity.getGoogleKey(), googlePwd);
                if (!isValid) {
                    throw new CustomerException("google验证码不正确, 请检查", 999999);
                }
            }

            HttpSession session = SessionAware.getSession();
            session.setAttribute(Constants.SESSION_USER_KEY, userEntity);
            session.setAttribute("userId", userEntity.getId());


            List<SysResourceEntity> rootResources = adminService.queryRootMenus(userEntity.getId());
            session.setAttribute("rootMenus", rootResources);

            Map<String, List<SysUserMenuEntity>> leftMenuMap = new HashMap<>();
            List<SysUserMenuEntity> leftMenus = null;
            for (SysResourceEntity rootResource : rootResources) {
                leftMenus = adminService.queryLeftMenus(userEntity.getId(), rootResource.getId(), false);
                leftMenuMap.put("root_" + rootResource.getId(), leftMenus);
            }
            for (String s : leftMenuMap.keySet()) {
                log.info("{}:{}", s, JSON.toJSONString(leftMenuMap.get(s)));
            }
            session.setAttribute("leftMenuMap", leftMenuMap);
            if (CollectionUtils.isNotEmpty(rootResources)) {
                session.setAttribute("currentRoot", "root_" + rootResources.get(0).getId());
            }

            adminUtils.putGrantedResourceIntoSession(adminService.queryUserResources(userEntity.getId()), session);

            if (!Boolean.valueOf(isDev) && bindGoogleKey) {
                return "redirect:bind_googleauth";
            }

            if(request.getRequestURL().toString().contains("admin.3f-sports.com")){
                return "redirect:" + backUrl + "/index";
            }else {
                return "redirect:" + request.getRequestURL().toString().replaceAll(request.getRequestURI(),"") + "/index";
            }
        } catch (CustomerException e) {
            model.addAttribute("message", e.toString());
            return "login";
        } catch (Exception e) {
            model.addAttribute("message", "登录系统失败, 错误信息:" + e.toString());
            return "login";
        }
    }

    @RequestMapping(value = "bind_googleauth", method = RequestMethod.GET)
    public String gotoGoogleauth(Model model) throws Exception {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secretKey = key.getKey();
        SysUserEntity user = (SysUserEntity) SessionAware.getSession().getAttribute(Constants.SESSION_USER_KEY);
        String QRStr = String.format("otpauth://totp/3f-sports.com:%s?secret=%s", user.getEmail(), secretKey);
        String imgBase64 = QRCodeUtils.createQRCode(QRStr);

        model.addAttribute("imgBase64", imgBase64);
        model.addAttribute("secretKey", secretKey);
        return "bind_googleauth";
    }

    @RequestMapping(value = "bind_googleauth", method = RequestMethod.POST)
    public String bind_googleauth(Model model, @RequestParam String secretKey, @RequestParam Integer googlePwd) throws Exception {
        SysUserEntity user = (SysUserEntity) SessionAware.getSession().getAttribute(Constants.SESSION_USER_KEY);

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        if (gAuth.authorize(secretKey, googlePwd)) {
            adminService.updateGoogleKey(user.getId(), secretKey);
            user.setGoogleKey(secretKey);
            SessionAware.getSession().setAttribute(Constants.SESSION_USER_KEY, user);
        } else {
//            throw new CustomerException("google认证期绑定失败", 999999);
            return CommonUtils.jumpForFail(model, "/bind_googleauth", "google认证期绑定失败");
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET})
    public String logout() {
        HttpSession session = SessionAware.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("类已经被初始化, isDev的参数值:{}, domainUrl:{}", isDev, domainUrl);
    }
}
