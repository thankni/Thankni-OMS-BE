package com.ldsmsoft.framework.dao.mybatis.model;

import java.lang.Long;
import java.util.Date;

public class SYSRoleBean {
    private Long roleId;

    private String roleName;

    private String discription;

    private Long createuser;

    private Date createdtm;

    private Long updateuser;

    private Date updatedtm;

    private String status;

    private String hashcode;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription == null ? null : discription.trim();
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedtm() {
        return createdtm;
    }

    public void setCreatedtm(Date createdtm) {
        this.createdtm = createdtm;
    }

    public Long getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Long updateuser) {
        this.updateuser = updateuser;
    }

    public Date getUpdatedtm() {
        return updatedtm;
    }

    public void setUpdatedtm(Date updatedtm) {
        this.updatedtm = updatedtm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode == null ? null : hashcode.trim();
    }
}