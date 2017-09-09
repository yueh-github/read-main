package com.readmain.common.enums;

public enum ESysResourceType {

    MENU(1, "菜单"), LINK(2, "链接");

    private int code;

    private String name;

    ESysResourceType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
