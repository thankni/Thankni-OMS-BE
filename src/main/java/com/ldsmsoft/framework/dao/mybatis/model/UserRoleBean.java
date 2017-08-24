package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class UserRoleBean {
    private Long id;

    private Long fkUserId;

    private Long fkRoleId;

    private Date createTime;

    private Long fkCreateUserId;

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

    public Long getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getFkCreateUserId() {
        return fkCreateUserId;
    }

    public void setFkCreateUserId(Long fkCreateUserId) {
        this.fkCreateUserId = fkCreateUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}