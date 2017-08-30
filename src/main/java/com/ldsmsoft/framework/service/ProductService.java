/**
 * 商品的业务类
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.ProductionBeanWithBLOBs;
import com.ldsmsoft.framework.dao.mybatis.model.ProductionPlanBean;

/**
 * @author Administrator
 *
 */
public interface ProductService {

	/**
	 * 验证参数是否符合要求
	 * @param sfzhm
	 * @param icno
	 * @return
	 */
	public String validateParams(String sfzhm,String icno);
	
	/**
	 * 查询信息 production
	 * @param clazzId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<ProductionBeanWithBLOBs> selectProductions(String clazzId,String page,String pageSize);
	/**
	 * 查询信息 productionPlan
	 * @param productionId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<ProductionPlanBean> selectProductionPlans(String productionId,String page,String pageSize);
	
	/**
	 * 查询信息（单条） production
	 * @param productId
	 * @return
	 */
	ProductionBeanWithBLOBs selectById(String productId);

	/**
	 * 新增商品信息
	 * @param bean
	 * @return
	 */
	int addProduction();
	/**
	 * 修改商品信息
	 * @param bean
	 * @return
	 */
	int editProduction();
	
	/**
	 * 新增商品计划信息
	 * @param bean
	 * @return
	 */
	int addProductionPlan();
	/**
	 * 修改商品计划信息
	 * @param bean
	 * @return
	 */
	int editProductionPlan();
}
