package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.SYSRoleBean;

public interface SYSRoleBeanMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SYSRoleBean record);

    int insertSelective(SYSRoleBean record);

    SYSRoleBean selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SYSRoleBean record);

    int updateByPrimaryKey(SYSRoleBean record);
    
    List<SYSRoleBean> selectByParams(HashMap<String,Object> params);
}