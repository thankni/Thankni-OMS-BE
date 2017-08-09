package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SYSRoleBean;
import java.math.BigDecimal;

public interface SYSRoleBeanMapper {
    int deleteByPrimaryKey(BigDecimal roleId);

    int insert(SYSRoleBean record);

    int insertSelective(SYSRoleBean record);

    SYSRoleBean selectByPrimaryKey(BigDecimal roleId);

    int updateByPrimaryKeySelective(SYSRoleBean record);

    int updateByPrimaryKey(SYSRoleBean record);
}