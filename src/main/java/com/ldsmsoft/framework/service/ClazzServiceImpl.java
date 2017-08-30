package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.ClazzBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.ClazzBean;

@Service("ClazzService")
public class ClazzServiceImpl implements ClazzService{

	@Autowired
	private ClazzBeanMapper clazzMapper;
	
	/**
	 * 查询商品分类信息
	 */
	@Override
	public List<ClazzBean> selectByParams() {
		HashMap<String, Object> params = new HashMap<String,Object>();
		List<ClazzBean> clazzList = clazzMapper.selectByParams(params);
		return clazzList;
	}
	
	/**
	 * 新增商品分类信息
	 */
	@Override
	public int insert(ClazzBean bean) {
		
		int res = clazzMapper.insertSelective(bean);
		return res;
	}
	/**
	 * 修改商品分类信息
	 */
	@Override
	public int eidt(ClazzBean bean) {
		int res = clazzMapper.updateByPrimaryKeySelective(bean);
		return res;
	}

}
