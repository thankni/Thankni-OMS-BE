package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SYSRoleResourceBean;
import java.math.BigDecimal;

public interface SYSRoleResourceBeanMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SYSRoleResourceBean record);

    int insertSelective(SYSRoleResourceBean record);

    SYSRoleResourceBean selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SYSRoleResourceBean record);

    int updateByPrimaryKey(SYSRoleResourceBean record);
}