package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.ClazzBean;

/**
 * @author Administrator
 *
 */
public interface ClazzService {

	/**
	 * 查询商品分类信息
	 * @return
	 */
	public HashMap<String, Object> selectByParams();
	
	/**
	 * 修改商品分类信息
	 * @param bean
	 * @return
	 */
	int eidt(ClazzBean bean);

	/**
	 * 新增商品分类信息
	 * @param bean
	 * @return
	 */
	int insert(ClazzBean bean);


}
