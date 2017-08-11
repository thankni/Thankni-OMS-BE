package com.ldsmsoft.framework.dao.mybatis.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthClientBean {
    private Long authId;

    private String clientId;

    private String clentName;

    private String clientSecret;
    
    private String invalidTime;

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClentName() {
        return clentName;
    }

    public void setClentName(String clentName) {
        this.clentName = clentName == null ? null : clentName.trim();
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

	public String getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}
}