package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.BuildBean;

public interface BuildBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuildBean record);

    int insertSelective(BuildBean record);

    BuildBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuildBean record);

    int updateByPrimaryKey(BuildBean record);
}