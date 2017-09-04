package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.ProductionBean;
import com.ldsmsoft.framework.dao.mybatis.model.ProductionBeanWithBLOBs;

public interface ProductionBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductionBeanWithBLOBs record);

    int insertSelective(ProductionBeanWithBLOBs record);

    ProductionBeanWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductionBeanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProductionBeanWithBLOBs record);

    int updateByPrimaryKey(ProductionBean record);

    List<ProductionBeanWithBLOBs> selectByParams(HashMap<String,Object> params);
}