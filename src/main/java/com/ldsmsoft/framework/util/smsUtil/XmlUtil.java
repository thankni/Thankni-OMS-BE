package com.ldsmsoft.framework.util.smsUtil;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * 
 * @author ml
 *
 */
public class XmlUtil {


	/**
	 * xml格式如下： 
	 * <?xml version="1.0" encoding="utf-8"?>
	 * <Root version="2.0" User="****" PWD="****" UserType="8" CorpCode="*****">
	 * <SMS>
	 * <M>发送内容（BASE64）</M>
	 * <H M="目标手机号码"/>   多个手机号码用逗号分开。如:13937100001,13937100002
	 * </SMS>
	 * </Root>
	 * 
	 * 生成下行post请求的Xml加密过的消息，加密方式为Base64
	 * @param userType
	 * @param version
	 * @param userName
	 * @param pwd
	 * @param corpCode
	 * @param message
	 * @param phoneNum
	 * @return String
	 */
	public static String createDownSendXmlMessage(String userType,String version,String userName, String pwd, String corpCode, String message, String phoneNum){
		
		Document document = DocumentHelper.createDocument();
		//创建根节点
		Element root = DocumentHelper.createElement("Root");
		document.setRootElement(root);
		//设置编码格式
		document.setXMLEncoding("utf-8");
		//为root根节点添加属性以属性值
		root.addAttribute("version", version.trim());
		root.addAttribute("User", userName.trim());
		root.addAttribute("PWD", pwd.trim());
		root.addAttribute("UserType", userType.trim());
		root.addAttribute("CorpCode", corpCode.trim());
		
		Element sms = root.addElement("SMS");
		Element m = sms.addElement("M");
		//对消息内容进行Base64加密
		m.setText(Base64Util.getBase64(message));
		
		Element h = sms.addElement("H");
		h.addAttribute("M", phoneNum);
		
		return Base64Util.getBase64(document.asXML());
	}
	
	/**
	 * <?xml version="1.0" encoding="utf-8"?>
	 * <Root>
	 *  <SMS>
	 *    <Return State="0">提交成功（Base64编码）</Return>
	 * 	</SMS>
	 * </Root>
	 * 
	 * 解析下行请求后返回的xml消息，返回一个map
	 * @param xmlMes
	 * @return
	 */
	public static Map<String,String> parsDownReceiveXmlMessage(String xmlMes){
		HashMap<String,String> map = new HashMap<String, String>();
		
			if(xmlMes.length()<0 || xmlMes == null) 
				return null;

			//先对返回的Xml文本进行解密
			xmlMes = Base64Util.parsBase64(xmlMes);

			Document document;
			try {
				document = DocumentHelper.parseText(xmlMes);
				Element root = document.getRootElement();
				Element sms = root.element("SMS");
				Element res = sms.element("Return");
				Attribute state = res.attribute("State");
				if(res != null && state != null){
					//由于返回的的xml的消息内容是进过加密的，所在在对xml解密后，解析xml时还需要对内容进行解密
					map.put(res.getName(), Base64Util.parsBase64(res.getText()));
					map.put(state.getName(), state.getValue());
				}
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return map;
	}
	
	
	/**
	 * <?xml version="1.0" encoding="utf-8"?>
	 * <Root version="2.0" User="****" PWD="****" UserType="8" CorpCode="*****">
	 * </Root>
	 * 生成上行请求数据的xml
	 * @param version  版本号
	 * @param userName 用户名
	 * @param pwd  用户密码
	 * @param userType 用户类型
	 * @param corpCode 信箱号
	 * @return
	 */
	public static String createUpSendXmlMessage(String version,String userName,String pwd,String userType,String corpCode){
		String xml = "";
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("utf-8");
		Element root = DocumentHelper.createElement("Root");
		document.setRootElement(root);
		
		root.addAttribute("version", version.trim());
		root.addAttribute("User", userName.trim());
		root.addAttribute("PWD", pwd.trim());
		root.addAttribute("UserType", userType.trim());
		root.addAttribute("CorpCode", corpCode.trim());
		
		if(document != null) 
			xml = document.asXML();
		
		return Base64Util.getBase64(xml);
	}
	
	/**
	 * <?xml version="1.0" encoding="utf-8"?>
	 * <Root>
	 * <Return State="0">查询成功（Base64编码）</Return>
	 * <Deliver Src="139********" CorpCode="*****">
	 * 短信内容Base64
	 * </Deliver>
	 * </Root>
	 * 
	 * 解析上行返回的xml数据，返回一个map
	 * @param xmlMessage 
	 * @return Map
	 */
	public static Map<String, String> parsUpReceiveXmlMessage(String xmlMessage){
		Map<String, String> map = new HashMap<String, String>();
		xmlMessage = Base64Util.parsBase64(xmlMessage);
		Document document;
		try {
			
			document = DocumentHelper.parseText(xmlMessage);
			Element root = document.getRootElement();
			Element return_ = root.element("Return");
			Attribute state = return_.attribute("State");
			map.put(return_.getName(), Base64Util.parsBase64(return_.getText()));
			map.put(state.getName(), state.getValue());
			
			Element deliver = root.element("Deliver");
			if(deliver != null){
			Attribute src = deliver.attribute("Src");
			Attribute corpCode = deliver.attribute("CorpCode");
			map.put(deliver.getName(), deliver.getText());
			map.put(src.getName(), src.getValue());
			map.put(corpCode.getName(), corpCode.getValue());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return map;
	}
}
