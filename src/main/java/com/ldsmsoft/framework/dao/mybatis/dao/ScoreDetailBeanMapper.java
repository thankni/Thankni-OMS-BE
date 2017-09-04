package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ScoreDetailBean;

public interface ScoreDetailBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScoreDetailBean record);

    int insertSelective(ScoreDetailBean record);

    ScoreDetailBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScoreDetailBean record);

    int updateByPrimaryKey(ScoreDetailBean record);
}