package com.ldsmsoft.framework.webservice.server;

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
	public HashMap<String, Object> regUser(String name, String idcard, String icno, String tel, String password) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
			
			//用户姓名不能为空
			if(!Util.isEmpty(name)){
				//手机号姓名不能为空
				if(!Util.isEmpty(tel)){
					//密码不能为空
					if(!Util.isEmpty(password)){
						//resultMap=userService.regUser(name, idcard, icno, tel, password);
					}else{
						resultMap.put("message", "密码不能为空！");
						resultMap.put("resultType", "error");
					}
				}else{
					resultMap.put("message", "手机号姓名不能为空！");
					resultMap.put("resultType", "error");
				}
			}else{
				resultMap.put("message", "姓名不能为空！");
				resultMap.put("resultType", "error");
			}
		
		return resultMap;
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
			resultMap.put("msg", Common_Status.Common_Msg_301);
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
			List<SYSResourceBean> resList=resourceService.selectByUserId(Integer.parseInt(userId));
			resultMap.put("msg","数据获取成功！");
			resultMap.put("status",Common_Status.Common_Status_200);
			resultMap.put("result",resList);
		}else{
			resultMap.put("msg","userId不能为空！");
			resultMap.put("status",Common_Status.Common_Status_301);
		}
		return resultMap;
	}
}
