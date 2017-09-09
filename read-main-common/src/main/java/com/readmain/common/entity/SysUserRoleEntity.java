package com.readmain.common.entity;

import java.io.Serializable;

public class SysUserRoleEntity implements Serializable {

    private static final long serialVersionUID = -3927732670365321108L;

    private Long userId;

    private String roleIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
