package com.ldsmsoft.framework.dao.mybatis.model;

public class SMSProBean {
    private Short id;

    private String version;

    private String usertype;

    private String downurl;

    private String upurl;

    private String username;

    private String pwd;

    private String corpcode;

    private String idenexplain;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl == null ? null : downurl.trim();
    }

    public String getUpurl() {
        return upurl;
    }

    public void setUpurl(String upurl) {
        this.upurl = upurl == null ? null : upurl.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode == null ? null : corpcode.trim();
    }

    public String getIdenexplain() {
        return idenexplain;
    }

    public void setIdenexplain(String idenexplain) {
        this.idenexplain = idenexplain == null ? null : idenexplain.trim();
    }
}