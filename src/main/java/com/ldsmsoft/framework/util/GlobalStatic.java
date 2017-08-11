package com.ldsmsoft.framework.util;

public interface GlobalStatic {

		
	/**
	 * 接口全局状态码
	 */
	class Common_Status {
		/** 处理成功 */
		public static final String Common_Status_200 = "200";
		
		/** 400:服务发生异常 */
		public static final String Common_Status_400 = "400";
		
		/** 没有数据信息 */
		public static final String Common_Status_NULL = "100";
		
		/** 不能为空 */
		public static final String Common_Status_ISNULL = "101";
		
		/** 已存在,不能重复操作 */
		public static final String Common_Status_EXIST = "102";
		
		/** 用户名或密码错误**/
		public static final String Common_Status_301 = "301";
		public static final String Common_Msg_301 = "用户名或密码错误！";
		
		/**授权信息无效**/
		public static final String Common_Status_501 = "501";
		
	}
}
