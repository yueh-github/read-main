package com.readmain.common.exception;


import com.readmain.common.enums.EExceptionCode;

public class ApiException extends CustomerException {

    public ApiException(Integer code) {
        super(code);
    }

    public ApiException(String message, Integer code) {
        super(message, code);
    }

    public static ApiException build() {
        return new ApiException(EExceptionCode.SYS_ERROR.getMessage(), EExceptionCode.SYS_ERROR.getCode());
    }

    public static ApiException build(CustomerException e) {
        return new ApiException(e.getMessage(), e.getCode());
    }

    public ApiException(String message, Integer code, String sourceType) {
        super(message, code, sourceType);
    }

    public ApiException(String code, String message, String sourceType) {
        super(code, message, sourceType);
    }

    public ApiException(String message, Throwable cause, Integer code) {
        super(message, cause, code);
    }

    public ApiException(Throwable cause, Integer code) {
        super(cause, code);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace, code);
    }
}
