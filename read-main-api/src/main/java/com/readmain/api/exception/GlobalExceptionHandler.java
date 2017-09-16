package com.readmain.api.exception;

import com.google.gson.Gson;
import com.readmain.api.helper.JsonCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuehao on 2017/9/16.
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest request, Exception e) {
//        log.error(" ---- -- -- - -- - - - - - - - - - - 抛出异常信息 {}",e.getMessage());
        return new Gson().toJson(JsonCallback.builder().code(99999).message("system").success("false").build());
    }

    @ExceptionHandler(value = ReadmainException.class)
    public Object getDefaultException(HttpServletRequest request,ReadmainException e){
        return new Gson().toJson(JsonCallback.builder().code(e.getCode()).message(e.getMessage()).success("false").build());
    }

}
