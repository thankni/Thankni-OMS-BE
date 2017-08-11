package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.SYSResourceBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.SYSResourceBean;

@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private SYSResourceBeanMapper resourceMapper;
	
	/**
	 * 根据所传参数，查询权限信息
	 */
	@Override
	public List<SYSResourceBean> selectByParams(HashMap<String,Object> params) {
		List<SYSResourceBean> userList = resourceMapper.selectByParams(params);
		return userList;
	}
	/**
	 * 根据用户id，查询用户的权限信息
	 */
	@Override
	public List<SYSResourceBean> selectByUserId(Long userId) {
		List<SYSResourceBean> userList = resourceMapper.selectByUserId(userId);
		return userList;
	}
	

}
