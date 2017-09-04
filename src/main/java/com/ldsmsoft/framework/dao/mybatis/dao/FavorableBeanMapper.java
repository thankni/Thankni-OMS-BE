package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.FavorableBean;

public interface FavorableBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FavorableBean record);

    int insertSelective(FavorableBean record);

    FavorableBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FavorableBean record);

    int updateByPrimaryKeyWithBLOBs(FavorableBean record);

    int updateByPrimaryKey(FavorableBean record);
}