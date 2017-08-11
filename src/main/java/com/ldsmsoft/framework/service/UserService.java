/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.SYSUserBean;

/**
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 查询注册信息
	 * @param params
	 * @return
	 */
	public List<SYSUserBean> selectByParams(HashMap<String,Object> params);
	
	/**
	 * 用户登录
	 * @param idcard
	 * @param password
	 * @return
	 */
	HashMap<String, Object> getRegInfo(String loginName, String password,String token);

	/**
	 * 修改注册信息
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> eidtRegInfo(SYSUserBean bean);


}
