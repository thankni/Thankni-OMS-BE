package com.ldsmsoft.framework.util.smsUtil;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Util {

	/**
	 * Base64加密接收到的字符串
	 * @param xmlMes
	 * @return
	 */
	public static String getBase64(String xmlMes){
		
		String strBase64 = "";
		Base64 base64 = new Base64();
		byte[] b = null;
		try {
			b = xmlMes.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(b != null && b.length>0)
			try {
				strBase64 = new String(base64.encode(b),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return strBase64;	
	}
	
	/**
	 * Base64解密接收到的字符串
	 * @param xmlMes
	 * @return
	 */
	public static String parsBase64(String xmlMes){
		String parsStr = null;
		
		Base64 base64 = new Base64();
		byte[] b;
		try {
			b = base64.decode(xmlMes.getBytes("utf-8"));
			parsStr = new String(b,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return parsStr;
	}
}
