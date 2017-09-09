package com.readmain.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yuehao on 2017/8/27.
 */

@Data
public class UserEntity implements Serializable {

    private String name;

    private Integer age;

    public UserEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
