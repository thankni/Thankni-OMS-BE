package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.webconfig.AppException;
import com.ldsmsoft.framework.dao.mybatis.dao.ClazzBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.ClazzBean;
import com.ldsmsoft.framework.util.UtilEmpty;
import com.ldsmsoft.framework.util.DateUtil;
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
	public HashMap<String, Object> insert(ClazzBean bean) {
		// 分类名称校验
		if (UtilEmpty.isNullorEmpty(bean.getName())) {
			resultMap.put("msg", "分类名称name不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 分类描述校验
		if (UtilEmpty.isNullorEmpty(bean.getDescription())) {
			resultMap.put("msg", "分类描述dexcription不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}	
		
		try {
			bean.setCreateTime(DateUtil.getSqlDateForNow());
		} catch (AppException e) {
			e.printStackTrace();
		}
		bean.setStatus(1);
		
		int res = clazzMapper.insertSelective(bean);
		if (res > 0) {
			resultMap.put("msg", "处理成功！");
			resultMap.put("status", Common_Status.Common_Status_200);
			resultMap.put("result", "");
		} else {
			resultMap.put("msg", "处理失败，数据库处理异常（处理了0条数据），稍后再试 ！");
			resultMap.put("status", Common_Status.Common_Status_300);
			resultMap.put("result", "");
		}
		return resultMap;
	}
	/**
	 * 修改商品分类信息
	 */
	@Override
	public HashMap<String, Object> eidt(ClazzBean bean) {
		//分类id不能为空
		if (UtilEmpty.isNullorEmpty(bean.getId())) {
			resultMap.put("msg", "分类id不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
		// 分类名称校验
		if (UtilEmpty.isNullorEmpty(bean.getName())) {
			resultMap.put("msg", "分类名称name不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 分类描述校验
		if (UtilEmpty.isNullorEmpty(bean.getDescription())) {
			resultMap.put("msg", "分类描述dexcription不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}	
		
		try {
			bean.setUpdateTime(DateUtil.getSqlDateForNow());
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		int res = clazzMapper.updateByPrimaryKeySelective(bean);
		if (res > 0) {
			resultMap.put("msg", "处理成功！");
			resultMap.put("status", Common_Status.Common_Status_200);
			resultMap.put("result", "");
		} else {
			resultMap.put("msg", "处理失败，数据库处理异常（处理了0条数据），稍后再试 ！");
			resultMap.put("status", Common_Status.Common_Status_300);
			resultMap.put("result", "");
		}
		return resultMap;
	}

}
