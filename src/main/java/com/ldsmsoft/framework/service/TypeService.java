/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.TypeBean;

/**
 * @author Administrator
 *
 */
public interface TypeService {

	/**
	 * 查询信息
	 * @param params
	 * @return
	 */
	public List<TypeBean> selectByParams(HashMap<String,Object> params);
	
	/**
	 * 修改信息
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> eidt(TypeBean bean);


}
