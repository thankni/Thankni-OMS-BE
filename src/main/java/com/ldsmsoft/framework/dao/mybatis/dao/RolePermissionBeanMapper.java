package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.RolePermissionBean;

public interface RolePermissionBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermissionBean record);

    int insertSelective(RolePermissionBean record);

    RolePermissionBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermissionBean record);

    int updateByPrimaryKey(RolePermissionBean record);
}