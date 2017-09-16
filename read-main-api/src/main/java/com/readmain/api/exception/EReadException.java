package com.readmain.api.exception;

/**
 * Created by yuehao on 2017/9/16.
 */
public enum EReadException {

    SYS_ERROR(999999, "通信失败，请稍后再试！"),
    SYS_WARN(000001, "系统警告！");


    EReadException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReadException buildReadException() {
        return new ReadException(this.getMessage(), this.getCode());
    }
}
