package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.SessionBean;

public interface SessionBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SessionBean record);

    int insertSelective(SessionBean record);

    SessionBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SessionBean record);

    int updateByPrimaryKey(SessionBean record);
}