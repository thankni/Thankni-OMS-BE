package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BindInfoBean {
    private Long bindId;

    private String clientId;

    private String clientUserid;

    private Short isvalid;

    private Date bindDate;

    private Date unbindDate;

    private Long grbh;
    
    private String name;

    private String idcard;

    private String icno;

    public Long getBindId() {
        return bindId;
    }

    public void setBindId(Long bindId) {
        this.bindId = bindId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClientUserid() {
        return clientUserid;
    }

    public void setClientUserid(String clientUserid) {
        this.clientUserid = clientUserid == null ? null : clientUserid.trim();
    }

    public Short getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Short isvalid) {
        this.isvalid = isvalid;
    }

    public Long getGrbh() {
        return grbh;
    }

    public void setGrbh(Long grbh) {
        this.grbh = grbh;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIcno() {
		return icno;
	}

	public void setIcno(String icno) {
		this.icno = icno;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public Date getUnbindDate() {
		return unbindDate;
	}

	public void setUnbindDate(Date unbindDate) {
		this.unbindDate = unbindDate;
	}

}