package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.UniversityBean;

public interface UniversityBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UniversityBean record);

    int insertSelective(UniversityBean record);

    UniversityBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UniversityBean record);

    int updateByPrimaryKey(UniversityBean record);
}