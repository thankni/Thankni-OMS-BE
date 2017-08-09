package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SYSUserRoleBean;
import java.math.BigDecimal;

public interface SYSUserRoleBeanMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SYSUserRoleBean record);

    int insertSelective(SYSUserRoleBean record);

    SYSUserRoleBean selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SYSUserRoleBean record);

    int updateByPrimaryKey(SYSUserRoleBean record);
}