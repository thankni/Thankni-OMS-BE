package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.TypeBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.TypeBean;

@Service("TypeService")
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeBeanMapper typeMapper;
	
	/**
	 * 根据所传参数，查询信息
	 */
	@Override
	public List<TypeBean> selectByParams(HashMap<String, Object> params) {
		List<TypeBean> userList = typeMapper.selectByParams(params);
		return userList;
	}
	
	/**
	 * 修改信息
	 */
	@Override
	public HashMap<String, Object> eidt(TypeBean bean) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		
		typeMapper.updateByPrimaryKeySelective(bean);
		
		return resultMap;
	}

}
