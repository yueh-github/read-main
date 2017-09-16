package com.readmain.api.exception;

/**
 * Created by yuehao on 2017/9/16.
 */

public class ReadmainException extends Exception {

    private String message;

    private Integer code;

    public ReadmainException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
