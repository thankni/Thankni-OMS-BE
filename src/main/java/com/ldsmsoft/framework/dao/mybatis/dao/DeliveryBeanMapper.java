package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.DeliveryBean;

public interface DeliveryBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryBean record);

    int insertSelective(DeliveryBean record);

    DeliveryBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryBean record);

    int updateByPrimaryKey(DeliveryBean record);
}