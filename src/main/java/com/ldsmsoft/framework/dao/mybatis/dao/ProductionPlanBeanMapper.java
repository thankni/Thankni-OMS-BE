package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.ProductionPlanBean;

public interface ProductionPlanBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductionPlanBean record);

    int insertSelective(ProductionPlanBean record);

    ProductionPlanBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductionPlanBean record);

    int updateByPrimaryKey(ProductionPlanBean record);

	List<ProductionPlanBean> selectByParams(HashMap<String, Object> map);
}