package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.SYSUserBean;

public interface SYSUserBeanMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(SYSUserBean record);

    int insertSelective(SYSUserBean record);

    SYSUserBean selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SYSUserBean record);

    int updateByPrimaryKey(SYSUserBean record);
    
    List<SYSUserBean> selectByParams(HashMap<String,Object> params);
}