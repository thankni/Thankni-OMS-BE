package com.ldsmsoft.framework.util;

public interface GlobalStatic {

		
	/**
	 * 接口全局状态码
	 */
	class Common_Status {
		/** 处理成功 */
		public static final String Common_Status_200 = "200";
		/** 400:服务发生异常,请联系服务提供商！ */
		public static final String Common_Status_400 = "400";
		
		/** 没有数据信息 */
		public static final String Common_Status_NULL = "000";
		
		/** 不能为空 */
		public static final String Common_Status_ISNULL = "0000";
		
		/** 已存在,不能重复操作 */
		public static final String Common_Status_EXIST = "111";
		
	}
}
