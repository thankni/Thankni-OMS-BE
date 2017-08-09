package com.ldsmsoft.framework.util.smsUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ldsmsoft.framework.dao.mybatis.dao.SMSProBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.SMSProBean;

public class SMSSendUtil {
	
	//上行url
	private static String upUrl;
	//下行url
	private static String downUrl;
	//用户名
	private static String userName;
	//用户密码
	private static String pwd;
	//版本号（默认为2）
	private static String version;
	//用户类别（默认为8）
	private static String userType;
	//信箱号
	private static String corpCode;
	//验证码消息说明文本
	private static String idenExplain;
	
	@Autowired
	private static SMSProBeanMapper smsProMapper;
	
	/**
	 * 读取配置属性
	 */
	private static void readProperties(){
		
		//获取smsproperties信息
		SMSProBean smsBean = smsProMapper.selectByPrimaryKey(1);
		
		if(smsBean != null){
			version = smsBean.getVersion();
			userType = smsBean.getUsertype();
			downUrl = smsBean.getDownurl();
			upUrl = smsBean.getUpurl();
			userName = smsBean.getUsername();
			pwd = smsBean.getPwd();
			corpCode = smsBean.getCorpcode();
			idenExplain = smsBean.getIdenexplain();
			
		}
	}
	
	/**
	 * 向目标手机号发送验证码信息，发送成功并且成功写入日志成功返回True，否则返回false
	 * @param phoneNum 发送的目标手机号，多个手机号中间用逗号隔开，如：183543458345,13809876789
	 */
	public static boolean sendDownSMS(String phoneNum){
		boolean res = false;
		//读取配置属性
		readProperties();
		//用来接收短信服务器的返回信息
		Map<String, String> map = null;
		
		//生成验证码信息,验证码为六位数字
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++){
			int random = (int) (Math.random()*9);
			sb.append(random);
		}
		String message=sb.toString()+idenExplain;

		// 获取xml格式的上行数据内容
		String xmlMessage = XmlUtil.createDownSendXmlMessage(userType, version,
				userName, pwd, corpCode, message.trim(), phoneNum.trim());
		PrintWriter out = null;
		BufferedReader br = null;
		try {
			URL url_ = new URL(downUrl.trim());
			URLConnection conn = url_.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 发送请求
			out = new PrintWriter(conn.getOutputStream());
			out.write(xmlMessage);
			out.flush();

			// 读取返回信息
			// 访问服务器后服务器的回应字符串
			String result = "";
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				result = result + line;
			}
			map = XmlUtil.parsDownReceiveXmlMessage(result);
			//判断是否发送成功,若发送成功，保存发送日志信息，并保存验证码
			if (map.get("State").equals("0")) {
				if (SMSUtil.saveLog(null,phoneNum, new Date(), message,sb.toString()))
					res = true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 向目标手机号发送验证码信息，发送成功并且成功写入日志成功返回True，否则返回false
	 * @param phoneNum 发送的目标手机号，多个手机号中间用逗号隔开，如：183543458345,13809876789
	 */
	public static boolean sendDownSMS(String phoneNum, Long smslogId){
		boolean res = false;
		//读取配置属性
		readProperties();
		//用来接收短信服务器的返回信息
		Map<String, String> map = null;
		
		//生成验证码信息,验证码为六位数字
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++){
			int random = (int) (Math.random()*9);
			sb.append(random);
		}
		String message=sb.toString()+idenExplain;

		// 获取xml格式的上行数据内容
		String xmlMessage = XmlUtil.createDownSendXmlMessage(userType, version,
				userName, pwd, corpCode, message.trim(), phoneNum.trim());
		PrintWriter out = null;
		BufferedReader br = null;
		try {
			URL url_ = new URL(downUrl.trim());
			URLConnection conn = url_.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 发送请求
			out = new PrintWriter(conn.getOutputStream());
			out.write(xmlMessage);
			out.flush();

			// 读取返回信息
			// 访问服务器后服务器的回应字符串
			String result = "";
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				result = result + line;
			}
			map = XmlUtil.parsDownReceiveXmlMessage(result);
			//判断是否发送成功,若发送成功，保存发送日志信息，并保存验证码
			if (map.get("State").equals("0")) {
				if (SMSUtil.saveLog(smslogId,phoneNum, new Date(), message,sb.toString()))
					res = true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 向目标手机号发送指定消息文本的短信信息，发送成功并且成功写入日志返回True，否则返回false
	 * 
	 * @param phoneNum 发送的目标手机号，多个手机号中间用逗号隔开，如：183543458345,13809876789
	 * @param message 发送的短信内容
	 */
	public static  boolean sendDownSMS(String phoneNum,String message){
		Map<String, String> map = null;
		boolean res = false;
		//读取配置文件
		readProperties();
		//获取xml格式的上行数据内容
		String xmlMessage = XmlUtil.createDownSendXmlMessage(userType, version, userName, pwd, corpCode, message.trim(), phoneNum.trim());
		PrintWriter out = null;
		BufferedReader br = null;
		try {
			URL url_ = new URL(downUrl.trim());
			URLConnection conn = url_.openConnection();
			conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            //发送请求
            out = new PrintWriter(conn.getOutputStream());
            out.write(xmlMessage);
            out.flush();
            
            //读取返回信息
           //访问服务器后服务器的回应字符串
    		String result = ""; 
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null){
            	result = result + line;
            }
            map = XmlUtil.parsDownReceiveXmlMessage(result);
            if(map.get("State").equals("0")){
            	if(SMSUtil.saveLog(null,phoneNum, new Date(), message,"")) res = true;
            }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 发送上行请求数据
	 * 返回map的key值说明：
	 * State：Post请求后服务器返回此次请求状态（成功返回值为：0）
	 * Return：Post请求后服务器返回的发送状态说明（成功返回值为：查询成功）
	 * 如果上行查询不成功，则没有以下返回内容，如果查询成功，才会有以下内容
	 * Deliver：短信的内容
	 * Src：手机号码
	 * CorpCode：信箱号
	 * 
	 * @return Map
	 */
	public static Map<String,String> sendUpSMS(){
		
		Map<String, String> map = null;
		// 读取配置文件
		readProperties();
		// 获取xml格式的上行数据内容
		String xmlMessage = XmlUtil.createUpSendXmlMessage(version, userName, pwd, userType, corpCode);
		
		PrintWriter out = null;
		BufferedReader br = null;
		try {
			URL url_ = new URL(upUrl.trim());
			URLConnection conn = url_.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 发送请求
			out = new PrintWriter(conn.getOutputStream());
			out.write(xmlMessage);
			out.flush();

			// 读取返回信息
			// 访问服务器后服务器的回应字符串
			String result = "";
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				result = result + line;
			}
			map = XmlUtil.parsUpReceiveXmlMessage(result);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
