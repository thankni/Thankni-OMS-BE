package com.ldsmsoft.framework.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class SYSRoleBean {
    private BigDecimal roleId;

    private String roleName;

    private String discription;

    private BigDecimal createuser;

    private Date createdtm;

    private BigDecimal updateuser;

    private Date updatedtm;

    private String status;

    private String hashcode;

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
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

    public BigDecimal getCreateuser() {
        return createuser;
    }

    public void setCreateuser(BigDecimal createuser) {
        this.createuser = createuser;
    }

    public Date getCreatedtm() {
        return createdtm;
    }

    public void setCreatedtm(Date createdtm) {
        this.createdtm = createdtm;
    }

    public BigDecimal getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(BigDecimal updateuser) {
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