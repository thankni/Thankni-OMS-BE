package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.SYSResourceBean;

public interface SYSResourceBeanMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(SYSResourceBean record);

    int insertSelective(SYSResourceBean record);

    SYSResourceBean selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(SYSResourceBean record);

    int updateByPrimaryKey(SYSResourceBean record);

    List<SYSResourceBean> selectByParams(HashMap<String,Object> params);
    
    List<SYSResourceBean> selectByUserId(Long userId);
}