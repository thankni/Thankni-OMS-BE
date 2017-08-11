package com.common.service;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.common.webconfig.AppException;

/**
 * ServicesXmlParser
 * 
 * @author wengsh
 * @date 2012-3-20
 * 
 */
public class ServicesUtil {

	private static String COMMON_CONFIG_XML_LOCTION = "/common.config.xml";
	
	private XmlParser xmlParser;
	
	private Log log=LogFactory.getLog(ServicesUtil.class);

	public ServicesUtil() throws AppException {
		InputStream in = getClass().getResourceAsStream(COMMON_CONFIG_XML_LOCTION);// 加载服务配置文件
		xmlParser = new XmlParser(in);
	}
    /**
     * 得到业务的工厂类名
     * @param businessTypeNo <code>String</code>
     * @return String
     * @exception AppException 如果在配置文件中找不到相应的业务类,抛出业务逻辑异常
     */
	public String getBusinessClassName(String name) throws AppException{
		Element element=xmlParser.selectNode("/service-config/businesses/business[@name='"+name+"']");
	    if(element==null){
	    	log.error("【业务逻辑错误】错误信息如下:找不到名字为【"+name+"】的业务xml配置信息!");
	    	throw new AppException(getConfigExceptionInfo("401"));
	    }
	    return element.getTextTrim();
	}
	/**
	 * 得到异常信息
	 * @param name
	 * @return
	 */
	public String getConfigExceptionInfo(String name) throws AppException{
		Element element=xmlParser.selectNode("/service-config/exceptions/exception[@name='"+name+"']");
		if(element==null){
	    	log.error("【业务逻辑错误】错误信息如下:找不到名字为【"+name+"】的异常配置配置信息!");
	    	throw new AppException(getConfigExceptionInfo("402"));
	    }
	    return element.getTextTrim();
	}
}
