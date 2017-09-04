package com.ldsmsoft.framework.dao.mybatis.model;

public class OrderBeanWithBLOBs extends OrderBean {
    private String remark;

	private String adminRemark;

	private String courierRemark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark == null ? null : adminRemark.trim();
	}

	public String getCourierRemark() {
		return courierRemark;
	}

	public void setCourierRemark(String courierRemark) {
		this.courierRemark = courierRemark == null ? null : courierRemark.trim();
	}

	private String remark;

    private String adminRemark;

    private String courierRemark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark == null ? null : adminRemark.trim();
    }

    public String getCourierRemark() {
        return courierRemark;
    }

    public void setCourierRemark(String courierRemark) {
        this.courierRemark = courierRemark == null ? null : courierRemark.trim();
    }
}