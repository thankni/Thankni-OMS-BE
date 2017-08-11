package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SMSProBean;

public interface SMSProBeanMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SMSProBean record);

    int insertSelective(SMSProBean record);

    SMSProBean selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(SMSProBean record);

    int updateByPrimaryKey(SMSProBean record);
}