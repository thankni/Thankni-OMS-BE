package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ShoppingCartBean;

public interface ShoppingCartBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCartBean record);

    int insertSelective(ShoppingCartBean record);

    ShoppingCartBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCartBean record);

    int updateByPrimaryKey(ShoppingCartBean record);
}