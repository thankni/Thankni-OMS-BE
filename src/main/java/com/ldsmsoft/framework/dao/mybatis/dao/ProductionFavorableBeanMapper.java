package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ProductionFavorableBean;

public interface ProductionFavorableBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductionFavorableBean record);

    int insertSelective(ProductionFavorableBean record);

    ProductionFavorableBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductionFavorableBean record);

    int updateByPrimaryKey(ProductionFavorableBean record);
}