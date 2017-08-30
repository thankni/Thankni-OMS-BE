package com.ldsmsoft.framework.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.ProductionBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.dao.ProductionPlanBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.ProductionBeanWithBLOBs;
import com.ldsmsoft.framework.dao.mybatis.model.ProductionPlanBean;
import com.ldsmsoft.framework.util.CheckIDCard;
import com.ldsmsoft.framework.util.Util;;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	
	private ProductionBeanMapper productMapper;
	private ProductionPlanBeanMapper productPlanMapper;
	
	// Logger instance
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	
	
	/**
	 * 查询信息 production list
	 */
	@Override
	public List<ProductionBeanWithBLOBs> selectProductions(String clazzId,String page,String pageSize) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("clazzId",clazzId);
		
		//页码
		if(!Util.isEmpty(page)){
			map.put("page", Integer.parseInt(page));
		}else{
			map.put("page", 1);
		}
		//每页条数
		if(!Util.isEmpty(pageSize)){
			map.put("pageSize", Integer.parseInt(pageSize));
		}else{
			map.put("pageSize", 10);
		}
		List<ProductionBeanWithBLOBs> list = productMapper.selectByParams(map);
		return list;
	}
	/**
	 * 查询信息 productionPlan list
	 */
	@Override
	public List<ProductionPlanBean> selectProductionPlans(String productionId,String page,String pageSize) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("fkProductionId",productionId);
		
		//页码
		if(!Util.isEmpty(page)){
			map.put("page", Integer.parseInt(page));
		}else{
			map.put("page", 1);
		}
		//每页条数
		if(!Util.isEmpty(pageSize)){
			map.put("pageSize", Integer.parseInt(pageSize));
		}else{
			map.put("pageSize", 10);
		}
		List<ProductionPlanBean> list = productPlanMapper.selectByParams(map);
		return list;
	}
	/**
	 * 查询信息（单条）production
	 */
	@Override
	public ProductionBeanWithBLOBs selectById(String productId) {
		return productMapper.selectByPrimaryKey(Long.parseLong(productId));
	}	
	/**
	 * 新增商品信息
	 */
	@Override
	public int addProduction() {

		ProductionBeanWithBLOBs bean = new ProductionBeanWithBLOBs();
		int res = productMapper.insertSelective(bean);
		return res;
	}
	/**
	 * 修改商品信息
	 */
	@Override
	public int editProduction() {

		ProductionBeanWithBLOBs bean = new ProductionBeanWithBLOBs();
		int res = productMapper.updateByPrimaryKeySelective(bean);
		return res;
	}
	/**
	 * 新增商品计划信息
	 */
	@Override
	public int addProductionPlan() {
		ProductionPlanBean bean = new ProductionPlanBean();
		int res = productPlanMapper.insertSelective(bean);
		return res;
	}
	/**
	 * 修改商品计划信息
	 */
	@Override
	public int editProductionPlan() {
		ProductionPlanBean bean = new ProductionPlanBean();
		int res = productPlanMapper.updateByPrimaryKeySelective(bean);
		return res;
	}

}
