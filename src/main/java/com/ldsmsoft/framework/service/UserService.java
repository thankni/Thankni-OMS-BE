/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.UserBean;

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
	public List<UserBean> selectByParams(HashMap<String,Object> params);
	
	/**
	 * 查询注册信息
	 * @param bean
	 * @return
	 */
	UserBean selectByUserBean(UserBean bean);
	
	/**
	 * 用户注册
	 * @param name
	 * @param idcard
	 * @param icno
	 * @param tel
	 * @param password
	 * @return
	 */
	HashMap<String, Object> regUser(String name, String idcard, String icno, String tel, String password);

	/**
	 * 用户登录
	 * @param idcard
	 * @param password
	 * @return
	 */
	HashMap<String, Object> getRegInfo(String idcard, String password);

	/**
	 * 修改注册信息
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> eidtRegInfo(UserBean bean);


}
