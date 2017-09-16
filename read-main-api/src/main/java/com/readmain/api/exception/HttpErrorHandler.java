//package com.readmain.api.exception;
//
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by yuehao on 2017/9/16.
// */
//public class HttpErrorHandler implements ErrorController {
//    private final static String ERROR_PATH = "/error";
//
//    /**
//     * Supports the HTML Error View
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = ERROR_PATH, produces = "text/html")
//    public String errorHtml(HttpServletRequest request) {
//        return "404";
//    }
//
//
//    /**
//     * Supports other formats like JSON, XML
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = ERROR_PATH)
//    @ResponseBody
//    public Object error(HttpServletRequest request) {
//        return "404";
//    }
//
//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
//}
