/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.SYSRoleBean;

/**
 * @author Administrator
 *
 */
public interface RoleService {

	/**
	 * 查询信息
	 * @param params
	 * @return
	 */
	public List<SYSRoleBean> selectByParams(HashMap<String,Object> params);
	
	/**
	 * 修改信息
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> eidt(SYSRoleBean bean);


}
