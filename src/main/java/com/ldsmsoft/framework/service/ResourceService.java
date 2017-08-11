/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.SYSResourceBean;

/**
 * @author Administrator
 *
 */
public interface ResourceService {

	/**
	 * 根据所传参数，查询权限信息
	 * @param params
	 * @return
	 */
	public List<SYSResourceBean> selectByParams(HashMap<String,Object> params);
	/**
	 * 根据用户id，查询用户的权限信息
	 * @param userId
	 * @return
	 */
	public List<SYSResourceBean> selectByUserId(Long userId);



}
