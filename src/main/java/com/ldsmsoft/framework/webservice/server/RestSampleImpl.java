package com.ldsmsoft.framework.webservice.server;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.ldsmsoft.framework.dao.mybatis.model.SYSResourceBean;
import com.ldsmsoft.framework.service.AuthClientService;
import com.ldsmsoft.framework.service.ResourceService;
import com.ldsmsoft.framework.service.UserService;
import com.ldsmsoft.framework.util.CheckIDCard;
import com.ldsmsoft.framework.util.CheckUtil;
import com.ldsmsoft.framework.util.FileReadUtil;
import com.ldsmsoft.framework.util.GlobalStatic.Common_Status;
import com.ldsmsoft.framework.util.Util;

public class RestSampleImpl implements RestSample {

	//卡管接口的user、pass和url
	private static final String STATIC_USER = FileReadUtil.ReadProperties("card.properties","user");
	private static final String STATIC_PASS = FileReadUtil.ReadProperties("card.properties","pass");
	private static final String STATIC_URL = FileReadUtil.ReadProperties("card.properties","url");
	
	@Autowired
	private AuthClientService authClientService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;

	@Context  
    private UriInfo uriInfo;
	
	@Context  
    private Request request; 
	
	@Override
	public String doGet() {
		return "this is get rest request";  
	}

	@Override
	public String doRequest(@PathParam("param") String param, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return "success";  
	}
	
	/**
	 * 注册
	 */
	@Override
	public HashMap<String, Object> regUser(String userName,String tel,String loginName, String password) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
			
		//用户姓名不能为空
		if(!Util.isEmpty(userName)){
			resultMap.put("msg", "姓名不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
		//手机号不能为空
		if(Util.isEmpty(tel)){
			resultMap.put("msg", "手机号不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
		//登录名不能为空
		if(Util.isEmpty(loginName)){
			resultMap.put("msg", "登录名不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
		//密码不能为空
		if(Util.isEmpty(password)){
			resultMap.put("msg", "密码不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
		return userService.reg(userName, tel, loginName, password);
	}
	
	/**
	 * 修改注册信息
	 */
	@Override
	public HashMap<String, Object> eidtRegInfo(String clientId, String clientSecret,String userId,String userName,String idcard,String tel,String email,String password,String token,String status) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		Long Id;
		
		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if(!"200".equals(state.get("status"))){
			return state;
		}
		
		//userId不能为空
		if(!Util.isEmpty(userId)){
			resultMap.put("msg", "userId不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
			return resultMap;
		}else{
			Id = Long.parseLong(userId);
		}
		//姓名校验
		if(!Util.isEmpty(tel)){
			if(!CheckUtil.checkName(userName)){
				resultMap.put("msg", "姓名格式错误！");
				resultMap.put("status",Common_Status.Common_Status_604);
				return resultMap;
			};
		}
		//邮箱校验
		if(!Util.isEmpty(email)){
			if(!CheckUtil.checkEmail(email)){
				resultMap.put("msg", "邮箱格式错误！");
				resultMap.put("status",Common_Status.Common_Status_602);
				return resultMap;
			};
		}
		//身份证号校验
		if(!Util.isEmpty(idcard)){
			String aac002_new;
			try {
				aac002_new = CheckIDCard.IDCardValidate(idcard);// 15位身份证号转换为18位
				if (aac002_new.equals("Error")) {
					resultMap.put("msg", "身份证号格式错误！");
					resultMap.put("status",Common_Status.Common_Status_603);
					return resultMap;
				}
				if (aac002_new.length() == 1) {
					resultMap.put("msg", "身份证号校验位出错，正确的为！"+aac002_new);
					resultMap.put("status",Common_Status.Common_Status_603);
					return resultMap;
				} 
			} catch (ParseException e) {
				e.printStackTrace();
				resultMap.put("msg", e.getMessage().toString());
				resultMap.put("status",Common_Status.Common_Status_400);
				return resultMap;
			}
		}
		//手机号校验
		if(!Util.isEmpty(tel)){
			if(!CheckUtil.checkPhone(tel)){
				resultMap.put("msg", "手机号格式错误！");
				resultMap.put("status",Common_Status.Common_Status_601);
				return resultMap;
			};
		}

		return userService.eidtRegInfo(Id, userName, idcard, tel, email, password, token, status);
	}

	/**
	 * 登录
	 */
	@Override
	public HashMap<String, Object> getPersonInfo(String clientId, String clientSecret, String loginName, String password,String token) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if(!"200".equals(state.get("status"))){
			return state;
		}
		//登录名和密码的非空校验		
		if(!Util.isEmpty(loginName) && !Util.isEmpty(password) && !Util.isEmpty(token)){
			//登录：获取注册信息
			resultMap=userService.getRegInfo(loginName, password,token);
		}else{
			resultMap.put("msg","用户名或密码错误！");
			resultMap.put("status",Common_Status.Common_Status_301);
		}
		return resultMap;
	}
	
	/**
	 * 获取用户的权限
	 */
	@Override
	public HashMap<String, Object> getPersonPower(String token,String clientId, String clientSecret, String userId) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if(!"200".equals(state.get("status"))){
			return state;
		}
		//登录名和密码的非空校验		
		if(!Util.isEmpty(userId)){
			//登录：获取注册信息
			List<SYSResourceBean> resList=resourceService.selectByUserId(Long.parseLong(userId));
			resultMap.put("msg","数据获取成功！");
			resultMap.put("status",Common_Status.Common_Status_200);
			resultMap.put("result",resList);
		}else{
			resultMap.put("msg","userId不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
		}
		return resultMap;
	}
}
