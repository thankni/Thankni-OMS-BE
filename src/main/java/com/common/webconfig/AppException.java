package com.common.webconfig;

/**
 * 应用异常
 * 
 */
public class AppException extends Exception {
	/**
     * 空构造函数
     */
	public AppException() {
		super();
	}
    /**
     * 带参构造函数
     * @param message
     */
	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message,Exception e) {
		super(message,e);
	}

}
