package com.readmain.api.helper;

import lombok.Builder;

import java.io.Serializable;

/**
 * Created by yuehao on 2017/9/16.
 */

@Builder
public class JsonCallback implements Serializable {
    private Integer code;

    private String message;

    private String success;
}
