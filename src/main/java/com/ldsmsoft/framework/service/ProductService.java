/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
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
	
	/**
	 * 查询信息（多条）
	 * @param typeid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<ProductBean> selectByParams(String typeId,String page,String pageSize);
	
	/**
	 * 查询信息（单条）
	 * @param typeid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	ProductBean selectById(String productId);

	/**
	 * 修改信息
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> eidt(ProductBean bean);
}
