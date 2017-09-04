package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class AddressBean {
    private Long id;

    private Long fkUserId;

    private Long fkUniversityId;

    private Long fkBuildId;

    private String detail;

    private String consigneeName;

    private String consigneePhone;

    private Integer default;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Long getFkUniversityId() {
        return fkUniversityId;
    }

    public void setFkUniversityId(Long fkUniversityId) {
        this.fkUniversityId = fkUniversityId;
    }

    public Long getFkBuildId() {
        return fkBuildId;
    }

    public void setFkBuildId(Long fkBuildId) {
        this.fkBuildId = fkBuildId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone == null ? null : consigneePhone.trim();
    }

    public Integer getDefault() {
        return default;
    }

    public void setDefault(Integer default) {
        this.default = default;
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