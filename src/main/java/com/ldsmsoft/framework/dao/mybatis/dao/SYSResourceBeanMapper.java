package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SYSResourceBean;
import java.math.BigDecimal;

public interface SYSResourceBeanMapper {
    int deleteByPrimaryKey(BigDecimal resourceId);

    int insert(SYSResourceBean record);

    int insertSelective(SYSResourceBean record);

    SYSResourceBean selectByPrimaryKey(BigDecimal resourceId);

    int updateByPrimaryKeySelective(SYSResourceBean record);

    int updateByPrimaryKey(SYSResourceBean record);
}