package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SYSLogBean;
import java.math.BigDecimal;

public interface SYSLogBeanMapper {
    int deleteByPrimaryKey(BigDecimal logId);

    int insert(SYSLogBean record);

    int insertSelective(SYSLogBean record);

    SYSLogBean selectByPrimaryKey(BigDecimal logId);

    int updateByPrimaryKeySelective(SYSLogBean record);

    int updateByPrimaryKey(SYSLogBean record);
}