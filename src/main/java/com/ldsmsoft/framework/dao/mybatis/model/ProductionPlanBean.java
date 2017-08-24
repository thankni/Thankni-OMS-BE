package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class ProductionPlanBean {
    private Long id;

    private Long fkProductionId;

    private String name;

    private Double price;

    private Double marketPrice;

    private Integer salesVolume;

    private Integer likes;

    private Integer unlikes;

    private Integer fkCreateUserId;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkProductionId() {
        return fkProductionId;
    }

    public void setFkProductionId(Long fkProductionId) {
        this.fkProductionId = fkProductionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getUnlikes() {
        return unlikes;
    }

    public void setUnlikes(Integer unlikes) {
        this.unlikes = unlikes;
    }

    public Integer getFkCreateUserId() {
        return fkCreateUserId;
    }

    public void setFkCreateUserId(Integer fkCreateUserId) {
        this.fkCreateUserId = fkCreateUserId;
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