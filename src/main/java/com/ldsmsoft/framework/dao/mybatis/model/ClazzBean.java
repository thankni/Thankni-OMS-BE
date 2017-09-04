package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class ClazzBean {
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Long fkCreateUserId;

    private Integer status;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}