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
	 * 注册
	 * @param userName
	 * @param tel
	 * @param loginName
	 * @param password
	 * @return
	 */
	HashMap<String, Object> reg(String userName, String tel, String loginName, String password);

	/**
	 * 修改注册信息
	 * @param bean
	 * @return
	 */
	HashMap<String, Object> eidtRegInfo(Long userId,String userName,String idcard,String tel,String email,String password,String token,String status);



}
