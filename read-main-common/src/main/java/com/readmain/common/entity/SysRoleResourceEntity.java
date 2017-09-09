package com.readmain.common.entity;

import java.io.Serializable;

public class SysRoleResourceEntity implements Serializable{

    private static final long serialVersionUID = -8697438471034283823L;

    private Long roleId;

    private String resourceIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }
}
