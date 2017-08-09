package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SYSUserBean;
import java.math.BigDecimal;

public interface SYSUserBeanMapper {
    int deleteByPrimaryKey(BigDecimal userId);

    int insert(SYSUserBean record);

    int insertSelective(SYSUserBean record);

    SYSUserBean selectByPrimaryKey(BigDecimal userId);

    int updateByPrimaryKeySelective(SYSUserBean record);

    int updateByPrimaryKey(SYSUserBean record);
}