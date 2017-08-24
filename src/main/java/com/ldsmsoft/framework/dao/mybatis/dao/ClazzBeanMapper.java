package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ClazzBean;

public interface ClazzBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClazzBean record);

    int insertSelective(ClazzBean record);

    ClazzBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClazzBean record);

    int updateByPrimaryKeyWithBLOBs(ClazzBean record);

    int updateByPrimaryKey(ClazzBean record);
}