package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class FavorableBean {
    private Long id;

    private String name;

    private Double baseLine;

    private Double freePrice;

    private Long fkProductionId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String remark;

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
        this.name = name == null ? null : name.trim();
    }

    public Double getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(Double baseLine) {
        this.baseLine = baseLine;
    }

    public Double getFreePrice() {
        return freePrice;
    }

    public void setFreePrice(Double freePrice) {
        this.freePrice = freePrice;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}