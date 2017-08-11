package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.AuthClientBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.AuthClientBean;
import com.ldsmsoft.framework.util.GlobalStatic.Common_Status;
import com.ldsmsoft.framework.util.MD5Util;

@Service("AuthClientService")
public class AuthClientServiceImpl implements AuthClientService{

	@Autowired
	private AuthClientBeanMapper authClientMapper;
	
	/**
	 * 验证授权是否存在及其有效性
	 */
	@Override
	public HashMap<String, Object> validateAuth(String clientId,String clientSecret) {
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		//客户端id校验
		if(clientId==null || "".equals(clientId)){
			resultMap.put("msg", "clientId不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
		//授权码校验
		if(clientSecret==null || "".equals(clientSecret)){
			resultMap.put("msg", "clientSecret不能为空！");
			resultMap.put("status",Common_Status.Common_Status_ISNULL);
			return resultMap;
		}
				
		try {
			//参数加密处理
			String clientSecretEncode = MD5Util.md5Encode(clientSecret);
			
			params.put("clientId", clientId);
			params.put("clientSecret", clientSecretEncode);
			
			//查询授权信息是否存在及其有效性
			List<AuthClientBean> authClientList = authClientMapper.validateAuth(params);
			if(authClientList.size()==0){
				resultMap.put("msg", "授权信息不存在或者已经失效，请联系管理员！");
				resultMap.put("status", Common_Status.Common_Status_501);
			}else{
				AuthClientBean authClientBean = authClientList.get(0);
				resultMap.put("result", authClientBean);
				resultMap.put("msg", "授权信息正常！");
				resultMap.put("status",Common_Status.Common_Status_200);
			}
		} catch (Exception e) {
			resultMap.put("msg",e.getMessage());
			resultMap.put("status",Common_Status.Common_Status_400);
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * 验证授权是否存在及其有效性
	 */
	@Override
	public String validateAuthStr(String clientId,String clientSecret) {
		String str = "error";
		if(clientId!=null && clientId!=""){
			if(clientSecret!=null && clientSecret!=""){
				
				HashMap<String, Object> params = new HashMap<String,Object>();
				//准备参数
				params.put("clientId", clientId);
				params.put("clientSecret", clientSecret);
				
				//查询授权信息是否存在及其有效性
				List<AuthClientBean> authClientList = authClientMapper.validateAuth(params);
				if(authClientList.isEmpty()){
					str = "error";
				}else if(authClientList.size()>1){
					str = "error";
				}else{
					str = "success";
				}
			}else{
				str = "error";
			}
		}else{
			str = "error";
		}
		return str;
	}

}
