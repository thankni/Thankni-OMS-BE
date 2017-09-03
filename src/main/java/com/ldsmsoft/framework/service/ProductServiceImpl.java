package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.ProductionBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.dao.ProductionPlanBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.ProductionBeanWithBLOBs;
import com.ldsmsoft.framework.dao.mybatis.model.ProductionPlanBean;
import com.ldsmsoft.framework.util.GlobalStatic.Common_Status;
import com.ldsmsoft.framework.util.UtilEmpty;;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	private ProductionBeanMapper productMapper;
	private ProductionPlanBeanMapper productPlanMapper;

	// Logger instance
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	HashMap<String, Object> resultMap = new HashMap<String, Object>();

	/**
	 * 查询信息 production list
	 */
	@Override
	public HashMap<String, Object> selectProductions(String clazzId, String page, String pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("clazzId", clazzId);

		// 页码
		if (!page.isEmpty()) {
			map.put("page", Integer.parseInt(page));
		} else {
			map.put("page", 1);
		}
		// 每页条数
		if (!pageSize.isEmpty()) {
			map.put("pageSize", Integer.parseInt(pageSize));
		} else {
			map.put("pageSize", 10);
		}

		if (clazzId.isEmpty()) {
			resultMap.put("msg", "clazzId不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
		List<ProductionBeanWithBLOBs> list = productMapper.selectByParams(map);
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
	 * 查询信息 productionPlan list
	 */
	@Override
	public HashMap<String, Object> selectProductionPlans(String productionId, String page, String pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		// 页码
		if (!page.isEmpty()) {
			map.put("page", Integer.parseInt(page));
		} else {
			map.put("page", 1);
		}
		// 每页条数
		if (!pageSize.isEmpty()) {
			map.put("pageSize", Integer.parseInt(pageSize));
		} else {
			map.put("pageSize", 10);
		}
		// 商品productionId校验
		if (productionId.isEmpty()) {
			resultMap.put("msg", "productionId不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			return resultMap;
		} else {
			map.put("fkProductionId", productionId);
		}

		List<ProductionPlanBean> list = productPlanMapper.selectByParams(map);
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
	 * 查询信息（单条）production
	 */
	@Override
	public HashMap<String, Object> selectProductionById(String procudtionId) {
		// 商品procudtionId校验
		if (!UtilEmpty.isNullorEmpty(procudtionId)) {
			resultMap.put("msg", "商品procudtionId不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		ProductionBeanWithBLOBs bean = productMapper.selectByPrimaryKey(Long.parseLong(procudtionId));
		if (bean != null) {
			resultMap.put("msg", "数据获取成功！");
			resultMap.put("status", Common_Status.Common_Status_200);
			resultMap.put("result", bean);
		} else {
			resultMap.put("msg", "根据当前查询条件，没有获取到数据！");
			resultMap.put("status", Common_Status.Common_Status_NULL);
			resultMap.put("result", "");
		}
		return resultMap;
	}

	/**
	 * 新增商品信息
	 */
	@Override
	public HashMap<String, Object> addProduction(ProductionBeanWithBLOBs bean) {

		// 商品名称校验
		if (!UtilEmpty.isNullorEmpty(bean.getName())) {
			resultMap.put("msg", "商品名称name不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 商品标签校验
		if (!UtilEmpty.isNullorEmpty(bean.getLabel())) {
			resultMap.put("msg", "商品标签label不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		int res = productMapper.insertSelective(bean);
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
	 * 修改商品信息
	 */
	@Override
	public HashMap<String, Object> editProduction(ProductionBeanWithBLOBs bean) {

		// 商品id校验
		if (!UtilEmpty.isNullorEmpty(bean.getId())) {
			resultMap.put("msg", "商品id不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 商品名称校验
		if (!UtilEmpty.isNullorEmpty(bean.getName())) {
			resultMap.put("msg", "商品名称name不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 商品标签校验
		if (!UtilEmpty.isNullorEmpty(bean.getLabel())) {
			resultMap.put("msg", "商品标签label不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		int res = productMapper.updateByPrimaryKeySelective(bean);
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
	 * 新增商品计划信息
	 */
	@Override
	public HashMap<String, Object> addProductionPlan(ProductionPlanBean bean) {

		// 商品id校验
		if (!UtilEmpty.isNullorEmpty(bean.getFkProductionId())) {
			resultMap.put("msg", "商品fkProductionId不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 商品计划名称校验
		if (!UtilEmpty.isNullorEmpty(bean.getName())) {
			resultMap.put("msg", "商品计划名称name不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		int res = productPlanMapper.insertSelective(bean);
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
	 * 修改商品计划信息
	 */
	@Override
	public HashMap<String, Object> editProductionPlan(ProductionPlanBean bean) {
		// 商品计划id校验
		if (!UtilEmpty.isNullorEmpty(bean.getId())) {
			resultMap.put("msg", "商品计划id不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 商品fkProductionId校验
		if (!UtilEmpty.isNullorEmpty(bean.getFkProductionId())) {
			resultMap.put("msg", "商品fkProductionId不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		// 商品计划名称校验
		if (!UtilEmpty.isNullorEmpty(bean.getName())) {
			resultMap.put("msg", "商品计划名称name不能为空！");
			resultMap.put("status", Common_Status.Common_Status_ISNULL);
			resultMap.put("result", "");
			return resultMap;
		}
		int res = productPlanMapper.updateByPrimaryKeySelective(bean);
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
