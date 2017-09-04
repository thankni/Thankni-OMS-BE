package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.OrderBean;
import com.ldsmsoft.framework.dao.mybatis.model.OrderBeanWithBLOBs;

public interface OrderBeanMapper {
    int deleteByPrimaryKey(Long id);

	int insert(OrderBeanWithBLOBs record);

	int insertSelective(OrderBeanWithBLOBs record);

	OrderBeanWithBLOBs selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(OrderBeanWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(OrderBeanWithBLOBs record);

	int updateByPrimaryKey(OrderBean record);

	int deleteByPrimaryKey(Long id);

    int insert(OrderBeanWithBLOBs record);

    int insertSelective(OrderBeanWithBLOBs record);

    OrderBeanWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderBeanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OrderBeanWithBLOBs record);

    int updateByPrimaryKey(OrderBean record);
}