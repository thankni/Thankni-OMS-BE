/**
 * 
 */
package com.ldsmsoft.framework.service;


import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 *
 */
public interface PersonService {

	/**
	 * 根据身份证号和险种类型获取用户个人编号
	 * @param shzhm
	 * @return
	 */
	Long getGrbhByParam(HashMap<String, Object> param);
	/**
	 * 根据身份证号和社保卡号获取用户信息
	 * @param param
	 * @return
	 */
	List<AC01ViewBean>  getPersonByParam(HashMap<String, Object> param);
	
	
	/**
	 * 验证参数是否符合要求
	 * @param sfzhm
	 * @param icno
	 * @return
	 */
	public String validateParams(String sfzhm,String icno);
	
	/**
	 * 获取用户信息(单条)
	 */
	AC01ViewBean getPerByParam(HashMap<String, Object> param);
}
