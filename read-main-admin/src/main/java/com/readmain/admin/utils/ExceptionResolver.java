//package com.readmain.admin.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.saf.panpan.common.entity.BaseExceptionObject;
//import com.saf.panpan.common.exception.CustomerException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//public class ExceptionResolver extends SimpleMappingExceptionResolver {
//
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
//        //拦截异常类型
//        BaseExceptionObject exceptionInfo = this.wrapperException(e);
//        //拦截ApiException 和 ajax异常类型
//        if (e instanceof ApiException || this.isAjaxRequest(request)) {
//            return this.doResolveAjaxException(response, exceptionInfo);
//        }
//        //处理其他问题
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("code", exceptionInfo.getCode());
//        errorMap.put("message", exceptionInfo.getMsg());
//        return new ModelAndView("error", errorMap);
//    }
//
//    /**
//     * 转换异常
//     *
//     * @param e 异常对象
//     * @return BaseException
//     */
//    private BaseExceptionObject wrapperException(Exception e) {
//        BaseExceptionObject baseException = new BaseExceptionObject();
//        baseException.setCode("999999");
//        baseException.setMsg("服务器发生内部错误，请稍后再试或联系客服人员。");
//        //转换异常
//        if (e instanceof CustomerException) {
//            CustomerException ex = (CustomerException) e;
//            baseException.setCode(ex.getCode().toString());
//            baseException.setMsg(ex.getMessage());
//        } else {
//            log.error("系统错误, {}", e);
//        }
//        return baseException;
//    }
//
//    /**
//     * 检测是否是ajax请求
//     *
//     * @param request 请求对象
//     * @return Boolean
//     */
//    private Boolean isAjaxRequest(HttpServletRequest request) {
//        try {
//            if (request.getHeader("accept").contains("application/json")) return true;
//            if (request.getHeader("X-Requested-With") == null) return false;
//            if (request.getHeader("X-Requested-With").contains("XMLHttpRequest")) return true;
//        } catch (Exception e) {
//            return false;
//        }
//        return false;
//    }
//
//    /**
//     * 处理ajax异常
//     *
//     * @param response      响应对象
//     * @param exceptionInfo 异常信息
//     * @return ModelAndView
//     */
//    private ModelAndView doResolveAjaxException(HttpServletResponse response, BaseExceptionObject exceptionInfo) {
//        response.setContentType("application/json;charset=UTF-8");
//        try {
//            PrintWriter writer = response.getWriter();
//            String jsonStr = JSON.toJSONString(exceptionInfo);
//            writer.write(jsonStr);
//            writer.flush();
//        } catch (IOException e) {
//            log.error("处理ajax请求, 返回异常信息{}", e);
//        }
//        return new ModelAndView();
//    }
//}
