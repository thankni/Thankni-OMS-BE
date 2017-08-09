/**
 * 
 */
package com.ldsmsoft.framework.service;

/**
 * @author Administrator
 *
 */
public interface MasService {

	/**
	 * 发送短信验证码
	 * @param phoneNum 手机号
	 * @return
	 */
	boolean sendSMS(String phoneNum);
	
	/**
	 * 用户验证码信息验证类，若验证成功则返回true，否则返回false。验证信息有效时间为5分钟，
	 * @param phoneNum 手机号
	 * @param identifyCode 用户输入的验证码
	 * @return
	 */
    boolean validating(String phoneNum,String identifyCode);

}
