package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ImageBean;

public interface ImageBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImageBean record);

    int insertSelective(ImageBean record);

    ImageBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImageBean record);

    int updateByPrimaryKeyWithBLOBs(ImageBean record);

    int updateByPrimaryKey(ImageBean record);
}