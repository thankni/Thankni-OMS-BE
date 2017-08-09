package com.ldsmsoft.framework.dao.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.AuthClientBean;

public interface AuthClientBeanMapper {
    int deleteByPrimaryKey(Long authId);

    int insert(AuthClientBean record);

    int insertSelective(AuthClientBean record);

    AuthClientBean selectByPrimaryKey(Long authId);

    int updateByPrimaryKeySelective(AuthClientBean record);

    int updateByPrimaryKey(AuthClientBean record);

    /**
     * 验证权限是否存在及其有效性
     * @param params
     * @return
     */
	List<AuthClientBean> validateAuth(HashMap<String, Object> params);
}