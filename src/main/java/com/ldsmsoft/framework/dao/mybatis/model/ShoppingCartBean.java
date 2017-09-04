package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class ShoppingCartBean {
    private Long id;

    private Long fkProductionPlanId;

    private Long fkUserId;

    private Integer amount;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String productionName;

    private Double productionPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkProductionPlanId() {
        return fkProductionPlanId;
    }

    public void setFkProductionPlanId(Long fkProductionPlanId) {
        this.fkProductionPlanId = fkProductionPlanId;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName == null ? null : productionName.trim();
    }

    public Double getProductionPrice() {
        return productionPrice;
    }

    public void setProductionPrice(Double productionPrice) {
        this.productionPrice = productionPrice;
    }
}