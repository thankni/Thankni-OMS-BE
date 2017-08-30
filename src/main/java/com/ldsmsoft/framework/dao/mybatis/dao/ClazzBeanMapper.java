package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.ClazzBean;

public interface ClazzBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClazzBean record);

    int insertSelective(ClazzBean record);

    ClazzBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClazzBean record);

    int updateByPrimaryKeyWithBLOBs(ClazzBean record);

    int updateByPrimaryKey(ClazzBean record);

    List<ClazzBean> selectByParams(HashMap<String,Object> params);
}