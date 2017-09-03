package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.ClazzBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.ClazzBean;
import com.ldsmsoft.framework.util.GlobalStatic.Common_Status;

@Service("ClazzService")
public class ClazzServiceImpl implements ClazzService{

	@Autowired
	private ClazzBeanMapper clazzMapper;

	HashMap<String, Object> resultMap = new HashMap<String, Object>();
	/**
	 * 查询商品分类信息
	 */
	@Override
	public HashMap<String, Object> selectByParams() {
		HashMap<String, Object> params = new HashMap<String,Object>();
		List<ClazzBean> list = clazzMapper.selectByParams(params);
		
		if (list.size() > 0) {
			resultMap.put("msg", "数据获取成功！");
			resultMap.put("status", Common_Status.Common_Status_200);
			resultMap.put("result", list);
		} else {
			resultMap.put("msg", "根据当前查询条件，没有获取到数据！");
			resultMap.put("status", Common_Status.Common_Status_NULL);
			resultMap.put("result", list);
		}		
		return resultMap;
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
