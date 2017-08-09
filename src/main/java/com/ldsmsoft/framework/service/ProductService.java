/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.ProductBean;

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
	
	List<ProductBean> getProductsByParams(String typeid,String page,String pageSize);
}
