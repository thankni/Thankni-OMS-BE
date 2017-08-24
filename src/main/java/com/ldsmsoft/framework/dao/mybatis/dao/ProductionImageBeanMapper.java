package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ProductionImageBean;

public interface ProductionImageBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductionImageBean record);

    int insertSelective(ProductionImageBean record);

    ProductionImageBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductionImageBean record);

    int updateByPrimaryKey(ProductionImageBean record);
}