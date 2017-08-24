package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class RolePermissionBean {
    private Long id;

    private Long fkRoleId;

    private Long fkPermissionId;

    private Long fkCreateUserId;

    private Date createTime;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public Long getFkPermissionId() {
        return fkPermissionId;
    }

    public void setFkPermissionId(Long fkPermissionId) {
        this.fkPermissionId = fkPermissionId;
    }

    public Long getFkCreateUserId() {
        return fkCreateUserId;
    }

    public void setFkCreateUserId(Long fkCreateUserId) {
        this.fkCreateUserId = fkCreateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}