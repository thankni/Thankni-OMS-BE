package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.AddressBean;

public interface AddressBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AddressBean record);

    int insertSelective(AddressBean record);

    AddressBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AddressBean record);

    int updateByPrimaryKey(AddressBean record);
}