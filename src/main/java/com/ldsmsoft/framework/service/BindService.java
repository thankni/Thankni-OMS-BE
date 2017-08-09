/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

import com.ldsmsoft.framework.dao.mybatis.model.BindInfoBean;

/**
 * @author Administrator
 *
 */
public interface BindService {

	public List<BindInfoBean> selectByParams(HashMap<String,Object> params);
	
	/**
	 * 绑定用户信息
	 * @param clientId
	 * @param clientUserid
	 * @param sfzhm
	 * @param icno
	 * @param name
	 * @return
	 */
	public HashMap<String, Object> bindUser(String clientId,String clientUserid,String sfzhm,String icno,String name);

	/**
	 * 解绑用户信息
	 * @param bindId
	 * @return
	 */
	HashMap<String, Object> unbindUser(Long bindId);
	/**
	 * 用户绑定信息查询
	 * @param clientId
	 * @param clientUserid
	 * @return
	 */
	HashMap<String, Object> getBindInfo(String clientId, String clientUserid);
}
