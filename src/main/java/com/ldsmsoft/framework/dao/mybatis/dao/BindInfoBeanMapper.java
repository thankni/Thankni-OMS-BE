package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.BindInfoBean;

public interface BindInfoBeanMapper {
    int deleteByPrimaryKey(Long bindId);

    int insert(BindInfoBean record);

    int insertSelective(BindInfoBean record);

    BindInfoBean selectByPrimaryKey(Long bindId);

    List<BindInfoBean> selectByParams(HashMap<String,Object> params);
    
    int updateByPrimaryKeySelective(BindInfoBean record);

    int updateByPrimaryKey(BindInfoBean record);
}