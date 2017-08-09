/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;

/**
 * @author Administrator
 *
 */
public interface AuthClientService {

	/**
	 * 验证授权是否存在及其有效性
	 * @param clientId
	 * @param clientSecret
	 * @return
	 */
	public HashMap<String, Object> validateAuth(String clientId,String clientSecret);

	String validateAuthStr(String clientId, String clientSecret);
}
