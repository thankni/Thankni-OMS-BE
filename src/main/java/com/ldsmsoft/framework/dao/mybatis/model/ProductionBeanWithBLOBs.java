package com.ldsmsoft.framework.dao.mybatis.model;

public class ProductionBeanWithBLOBs extends ProductionBean {
    private String description;

    private String label;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}