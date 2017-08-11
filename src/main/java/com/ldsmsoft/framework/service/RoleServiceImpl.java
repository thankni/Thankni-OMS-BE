package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.SYSRoleBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.SYSRoleBean;

@Service("RoleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private SYSRoleBeanMapper roleMapper;
	
	/**
	 * 根据所传参数，查询信息
	 */
	@Override
	public List<SYSRoleBean> selectByParams(HashMap<String, Object> params) {
		List<SYSRoleBean> userList = roleMapper.selectByParams(params);
		return userList;
	}
	
	/**
	 * 修改信息
	 */
	@Override
	public HashMap<String, Object> eidt(SYSRoleBean bean) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		
		roleMapper.updateByPrimaryKeySelective(bean);
		
		return resultMap;
	}

}
