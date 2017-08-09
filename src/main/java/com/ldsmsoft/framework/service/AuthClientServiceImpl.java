package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.AuthClientBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.AuthClientBean;
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
		if(clientId!=null && clientId!=""){
			if(clientSecret!=null && clientSecret!=""){
				
				try {
					//参数加密处理
					String clientSecretEncode = MD5Util.md5Encode(clientSecret);
					
					params.put("clientId", clientId);
					params.put("clientSecret", clientSecretEncode);
					
					//查询授权信息是否存在及其有效性
					List<AuthClientBean> authClientList = authClientMapper.validateAuth(params);
					if(authClientList.isEmpty()){
						resultMap.put("message", "授权信息不存在或者已经失效，请联系管理员！");
						resultMap.put("resultType", "error");
					}else if(authClientList.size()>1){
						resultMap.put("message", "授权信息存在重复记录，请联系管理员！");
						resultMap.put("resultType", "error");
					}else{
						AuthClientBean authClientBean = authClientList.get(0);
						resultMap.put("resultData", authClientBean);
						resultMap.put("message", "授权信息正常！");
						resultMap.put("resultType", "success");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else{
				resultMap.put("message", "clientSecret不能为空！");
				resultMap.put("resultType", "error");
			}
		}else{
			resultMap.put("message", "clientId不能为空！");
			resultMap.put("resultType", "error");
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
