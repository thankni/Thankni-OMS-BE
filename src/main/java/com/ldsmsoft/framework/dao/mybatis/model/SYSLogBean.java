package com.ldsmsoft.framework.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class SYSLogBean {
    private BigDecimal logId;

    private Object argsIn;

    private String code;

    private Object msg;

    private Date dtm;

    private String zhsqName;

    private Object resultOut;

    private String clientid;

    private BigDecimal userId;

    public BigDecimal getLogId() {
        return logId;
    }

    public void setLogId(BigDecimal logId) {
        this.logId = logId;
    }

    public Object getArgsIn() {
        return argsIn;
    }

    public void setArgsIn(Object argsIn) {
        this.argsIn = argsIn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Date getDtm() {
        return dtm;
    }

    public void setDtm(Date dtm) {
        this.dtm = dtm;
    }

    public String getZhsqName() {
        return zhsqName;
    }

    public void setZhsqName(String zhsqName) {
        this.zhsqName = zhsqName == null ? null : zhsqName.trim();
    }

    public Object getResultOut() {
        return resultOut;
    }

    public void setResultOut(Object resultOut) {
        this.resultOut = resultOut;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }
}