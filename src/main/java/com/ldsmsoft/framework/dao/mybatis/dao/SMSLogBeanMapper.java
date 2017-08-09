package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.SMSLogBean;

public interface SMSLogBeanMapper {
    void insert(SMSLogBean record);

    void insertSelective(SMSLogBean record);
    
    List<String> validateSMS(SMSLogBean record);
}