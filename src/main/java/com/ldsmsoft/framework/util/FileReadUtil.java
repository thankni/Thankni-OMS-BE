package com.ldsmsoft.framework.util;

import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;

import javax.ws.rs.client.Client;

public class FileReadUtil {

	/**
	 * 读取.properties文件
	 * @param fileName 文件名
	 * @param key 属性名
	 * @return
	 */
	public static String ReadProperties(String fileName,String key){
		
		String resultStr = "";
		Properties prop = new Properties();

		try {
			
			//防止出现中文乱码的问题：因为字节流是无法读取中文的，所以采取reader把inputStream转换成reader用字符流来读取中文
			prop.load(new InputStreamReader(Client.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
			Iterator<String> it=prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String param=it.next();
				if(key.equals(param)){
					resultStr=prop.getProperty(param);
				}
			}
			//bf.close();
			//in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStr;
		
	}
}
