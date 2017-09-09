package com.readmain.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysResourceTreeEntity implements Serializable {

    private static final long serialVersionUID = -8531306694924627464L;

    private Long currentResourceId;

    private SysResourceEntity resourceEntity;

    private List<SysResourceTreeEntity> sonResourceTree = new ArrayList<>();

    private Boolean checked;

    public SysResourceTreeEntity() {
    }

    public SysResourceTreeEntity(SysResourceEntity resource) {
        this.currentResourceId = resource.getId();
        this.resourceEntity = resource;
        this.checked = false;
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

    public List<SysResourceTreeEntity> getSonResourceTree() {
        return sonResourceTree;
    }

    public void setSonResourceTree(List<SysResourceTreeEntity> sonResourceTree) {
        this.sonResourceTree = sonResourceTree;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
