package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class ProductionImageBean {
    private Long id;

    private Integer sort;

    private Long fkImageId;

    private Long fkProductionId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getFkImageId() {
        return fkImageId;
    }

    public void setFkImageId(Long fkImageId) {
        this.fkImageId = fkImageId;
    }

    public Long getFkProductionId() {
        return fkProductionId;
    }

    public void setFkProductionId(Long fkProductionId) {
        this.fkProductionId = fkProductionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}