package com.ldsmsoft.framework.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderBean {
    private Long id;

	private String orderNumber;

	private BigDecimal price;

	private Integer fkAddressId;

	private Long fkDeliveryId;

	private Long fkUserId;

	private Long fkUpdateUserId;

	private Date createTime;

	private Date updateTime;

	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber == null ? null : orderNumber.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getFkAddressId() {
		return fkAddressId;
	}

	public void setFkAddressId(Integer fkAddressId) {
		this.fkAddressId = fkAddressId;
	}

	public Long getFkDeliveryId() {
		return fkDeliveryId;
	}

	public void setFkDeliveryId(Long fkDeliveryId) {
		this.fkDeliveryId = fkDeliveryId;
	}

	public Long getFkUserId() {
		return fkUserId;
	}

	public void setFkUserId(Long fkUserId) {
		this.fkUserId = fkUserId;
	}

	public Long getFkUpdateUserId() {
		return fkUpdateUserId;
	}

	public void setFkUpdateUserId(Long fkUpdateUserId) {
		this.fkUpdateUserId = fkUpdateUserId;
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

	private Long id;

    private String orderNumber;

    private BigDecimal price;

    private Integer fkAddressId;

    private Long fkDeliveryId;

    private Long fkUserId;

    private Long fkUpdateUserId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getFkAddressId() {
        return fkAddressId;
    }

    public void setFkAddressId(Integer fkAddressId) {
        this.fkAddressId = fkAddressId;
    }

    public Long getFkDeliveryId() {
        return fkDeliveryId;
    }

    public void setFkDeliveryId(Long fkDeliveryId) {
        this.fkDeliveryId = fkDeliveryId;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Long getFkUpdateUserId() {
        return fkUpdateUserId;
    }

    public void setFkUpdateUserId(Long fkUpdateUserId) {
        this.fkUpdateUserId = fkUpdateUserId;
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