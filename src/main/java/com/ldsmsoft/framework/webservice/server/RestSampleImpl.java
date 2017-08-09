package com.ldsmsoft.framework.webservice.server;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.ldsmsoft.framework.service.AuthClientService;
import com.ldsmsoft.framework.service.BindService;
import com.ldsmsoft.framework.service.PersonService;
import com.ldsmsoft.framework.service.UserService;
import com.ldsmsoft.framework.util.FileReadUtil;
import com.ldsmsoft.framework.util.Util;

public class RestSampleImpl implements RestSample {

	//卡管接口的user、pass和url
	private static final String STATIC_USER = FileReadUtil.ReadProperties("card.properties","user");
	private static final String STATIC_PASS = FileReadUtil.ReadProperties("card.properties","pass");
	private static final String STATIC_URL = FileReadUtil.ReadProperties("card.properties","url");
	
	@Autowired
	private PersonService personService;
	@Autowired
	private AuthClientService authClientService;
	@Autowired
	private BindService bindService;
	@Autowired
	private UserService userService;

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
	 * 获取用户信息(VIEW_ACO1)
	 */
	@Override
	public HashMap<String,Object> getPerson(String clientId, String clientSecret,String icno,String sfzhm) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		
		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if("success".equals(state.get("resultType"))){
		        
	    	//验证这几个参数是否符合要求
			String errorInfo = personService.validateParams(sfzhm,icno);
			if(("").equals(errorInfo)){
				
				param.put("sfzhm", sfzhm);
				param.put("icno", icno);
				/*List<AC01ViewBean> list = personService.getPersonByParam(param);
	        	if(list!=null){
	        		resultMap.put("resultType","success");
	        		resultMap.put("resultData",list);
	        	}else{
	        		resultMap.put("resultType","error");		    			 
	        		resultMap.put("message","该人信息不存在或者网络通讯异常，请稍后再试！");
	        	}*/
	        	return resultMap;			 
			}else{
			     resultMap.put("resultType","error");		    			 
		         resultMap.put("message",errorInfo);
		         return resultMap;
			}
		}else{
			resultMap.put("state", state.get("message").toString());
		}
		return resultMap;
	}	
	

	/**
	 * 用户绑定信息查询
	 */
	@Override
	public HashMap<String,Object> getBindInfo(String clientId,String clientSecret,String clientUserid) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();

		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if("success".equals(state.get("resultType"))){
		
			//用户账号不能为空
			if(!Util.isEmpty(clientUserid)){
				resultMap=bindService.getBindInfo(clientId, clientUserid);
			}else{
				resultMap.put("message", "客户端用户账号clientUserid不能为空！");
				resultMap.put("resultType", "error");
			}
		}else{
			resultMap.put("message", state.get("message").toString());
			resultMap.put("resultType","error");		    			 
		}
		return resultMap;
        
	}
	
	/**
	 * 绑定用户信息(微信等)
	 */
	@Override
	public HashMap<String,Object> bindUser(String clientId,String clientSecret,String clientUserid,String sfzhm,String icno,String name) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();

		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if("success".equals(state.get("resultType"))){
		
        	//验证这几个参数是否符合要求
    		String errorInfo = personService.validateParams(sfzhm,icno);
			if(("").equals(errorInfo)){
				
				//用户账号不能为空
				if(!Util.isEmpty(clientUserid)){
					resultMap=bindService.bindUser(clientId, clientUserid, sfzhm, icno,name);
				}else{
					resultMap.put("message", "客户端用户账号clientUserid不能为空！");
					resultMap.put("resultType", "error");
				}
			
			}else{
			     resultMap.put("resultType","error");		    			 
		         resultMap.put("message",errorInfo);
			}
		}else{
			resultMap.put("message", state.get("message").toString());
			resultMap.put("resultType","error");		    			 
		}
		return resultMap;
        
	}
	
	/**
	 * 解绑用户信息(微信等)
	 */
	@Override
	public HashMap<String, Object> unbindUser(String clientId, String clientSecret, String bindId) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();

		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if("success".equals(state.get("resultType"))){
			resultMap=bindService.unbindUser(Long.parseLong(bindId));
		}else{
			resultMap.put("message", state.get("message").toString());
			resultMap.put("resultType","error");		    			 
		}
		return resultMap;
	}


	/**
	 * 注册
	 */
	@Override
	public HashMap<String, Object> regUser(String name, String idcard, String icno, String tel, String password) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		//验证这几个参数是否符合要求
		String errorInfo = personService.validateParams(idcard,icno);
		if(("").equals(errorInfo)){
			
			//用户姓名不能为空
			if(!Util.isEmpty(name)){
				//手机号姓名不能为空
				if(!Util.isEmpty(tel)){
					//密码不能为空
					if(!Util.isEmpty(password)){
						resultMap=userService.regUser(name, idcard, icno, tel, password);
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
		
		}else{
		     resultMap.put("resultType","error");		    			 
	         resultMap.put("message",errorInfo);
		}
		return resultMap;
	}

	/**
	 * 登录
	 */
	@Override
	public HashMap<String, Object> getPersonInfo(String clientId, String clientSecret, String idcard, String password) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		//验证授权信息是否存在及是否有效
		HashMap<String, Object> state = authClientService.validateAuth(clientId,clientSecret);
		if("success".equals(state.get("resultType"))){
			if(!Util.isEmpty(idcard) && !Util.isEmpty(password)){
				//登录：获取注册信息
				resultMap=userService.getRegInfo(idcard, password);
			}else{
				resultMap.put("message", "用户名或密码不能为空！");
				resultMap.put("resultType","error");
			}
		}else{
			resultMap.put("message", state.get("message").toString());
			resultMap.put("resultType","error");		    			 
		}
		return resultMap;
	}
	
	
}
