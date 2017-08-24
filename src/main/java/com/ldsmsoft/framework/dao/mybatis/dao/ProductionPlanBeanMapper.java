package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ProductionPlanBean;

public interface ProductionPlanBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductionPlanBean record);

    int insertSelective(ProductionPlanBean record);

    ProductionPlanBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductionPlanBean record);

    int updateByPrimaryKey(ProductionPlanBean record);
}