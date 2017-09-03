/**
 * 商品的业务类
 */
package com.ldsmsoft.framework.service;

import java.util.HashMap;

import com.ldsmsoft.framework.dao.mybatis.model.ProductionBeanWithBLOBs;
import com.ldsmsoft.framework.dao.mybatis.model.ProductionPlanBean;

/**
 * @author Administrator
 *
 */
public interface ProductService {

	/**
	 * 查询信息 production
	 * 
	 * @param clazzId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	HashMap<String, Object> selectProductions(String clazzId, String page, String pageSize);

	/**
	 * 查询信息 productionPlan
	 * 
	 * @param productionId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	HashMap<String, Object> selectProductionPlans(String productionId, String page, String pageSize);

	/**
	 * 查询信息（单条） production
	 * 
	 * @param productId
	 * @return
	 */
	HashMap<String, Object> selectProductionById(String productId);

	/**
	 * 新增商品信息
	 * 
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> addProduction(ProductionBeanWithBLOBs bean);

	/**
	 * 修改商品信息
	 * 
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> editProduction(ProductionBeanWithBLOBs bean);

	/**
	 * 新增商品计划信息
	 * 
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> addProductionPlan(ProductionPlanBean productionPlan);

	/**
	 * 修改商品计划信息
	 * 
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> editProductionPlan(ProductionPlanBean productionPlan);
}
