package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.PermissionBean;

public interface PermissionBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionBean record);

    int insertSelective(PermissionBean record);

    PermissionBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionBean record);

    int updateByPrimaryKey(PermissionBean record);
}