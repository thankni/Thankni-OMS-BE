package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class ProductionBean {
    private Long id;

    private String name;

    private String field;

    private Long fkClazzId;

    private Long fkHeaderId;

    private Long fkCreateUserId;

    private Long fkFooterId;

    private Double discount;

    private Double costPrice;

    private Integer status;

    private Date createTime;

    private Date updateTime;

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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public Long getFkClazzId() {
        return fkClazzId;
    }

    public void setFkClazzId(Long fkClazzId) {
        this.fkClazzId = fkClazzId;
    }

    public Long getFkHeaderId() {
        return fkHeaderId;
    }

    public void setFkHeaderId(Long fkHeaderId) {
        this.fkHeaderId = fkHeaderId;
    }

    public Long getFkCreateUserId() {
        return fkCreateUserId;
    }

    public void setFkCreateUserId(Long fkCreateUserId) {
        this.fkCreateUserId = fkCreateUserId;
    }

    public Long getFkFooterId() {
        return fkFooterId;
    }

    public void setFkFooterId(Long fkFooterId) {
        this.fkFooterId = fkFooterId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}