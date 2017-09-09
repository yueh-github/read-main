package com.readmain.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysUserMenuEntity implements Serializable {

    private static final long serialVersionUID = 2780982022957725167L;

    private Long currentResourceId;

    private SysResourceEntity resourceEntity;

    private List<SysUserMenuEntity> sonMenuList = new ArrayList<>();

    public SysUserMenuEntity() {
    }

    public SysUserMenuEntity(SysResourceEntity resourceEntity) {
        super();
        this.currentResourceId = resourceEntity.getId();
        this.resourceEntity = resourceEntity;
    }

    public Long getCurrentResourceId() {
        return currentResourceId;
    }

    public void setCurrentResourceId(Long currentResourceId) {
        this.currentResourceId = currentResourceId;
    }

    public SysResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(SysResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    public List<SysUserMenuEntity> getSonMenuList() {
        return sonMenuList;
    }

    public void setSonMenuList(List<SysUserMenuEntity> sonMenuList) {
        this.sonMenuList = sonMenuList;
    }
}
