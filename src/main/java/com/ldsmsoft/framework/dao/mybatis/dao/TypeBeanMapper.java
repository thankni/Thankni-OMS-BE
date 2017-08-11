package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.TypeBean;

public interface TypeBeanMapper {
    int deleteByPrimaryKey(Long typeId);

    int insert(TypeBean record);

    int insertSelective(TypeBean record);

    TypeBean selectByPrimaryKey(Long typeId);

    int updateByPrimaryKeySelective(TypeBean record);

    int updateByPrimaryKey(TypeBean record);
    
    List<TypeBean> selectByParams(HashMap<String,Object> params);
}