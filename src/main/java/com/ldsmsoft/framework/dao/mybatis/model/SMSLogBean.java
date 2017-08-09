package com.ldsmsoft.framework.dao.mybatis.model;

import java.util.Date;

public class SMSLogBean {
    private Long smslogid;

    private String phonenum;

    private String log;

    private Date datetime;

    private String idencode;

    public Long getSmslogid() {
        return smslogid;
    }

    public void setSmslogid(Long smslogid) {
        this.smslogid = smslogid;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log == null ? null : log.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getIdencode() {
        return idencode;
    }

    public void setIdencode(String idencode) {
        this.idencode = idencode == null ? null : idencode.trim();
    }
}