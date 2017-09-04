package com.ldsmsoft.framework.dao.mybatis.model;

import java.lang.Long;
import java.util.Date;

public class TypeBean {
    private Long typeId;

    private String typeName;

    private String discription;

    private Date createdtm;

    private Long createuser;

    private Date updatedtm;

    private Long updateuser;

    private String status;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription == null ? null : discription.trim();
    }

    public Date getCreatedtm() {
        return createdtm;
    }

    public void setCreatedtm(Date createdtm) {
        this.createdtm = createdtm;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatedtm() {
        return updatedtm;
    }

    public void setUpdatedtm(Date updatedtm) {
        this.updatedtm = updatedtm;
    }

    public Long getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Long updateuser) {
        this.updateuser = updateuser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}