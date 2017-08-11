package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SYSLogBean;

public interface SYSLogBeanMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(SYSLogBean record);

    int insertSelective(SYSLogBean record);

    SYSLogBean selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(SYSLogBean record);

    int updateByPrimaryKey(SYSLogBean record);
}