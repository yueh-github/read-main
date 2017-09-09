package com.readmain.common.entity;

import java.io.Serializable;

public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = -6505146217394451112L;

    private Long id;

    private String name;

    private Boolean checked = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
