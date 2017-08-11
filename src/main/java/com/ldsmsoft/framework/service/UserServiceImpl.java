package com.ldsmsoft.framework.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.SYSUserBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.SYSUserBean;
import com.ldsmsoft.framework.util.Util;
import com.ldsmsoft.framework.util.GlobalStatic.Common_Status;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private SYSUserBeanMapper userMapper;
	
	/**
	 * 根据所传参数，查询用户注册信息
	 */
	@Override
	public List<SYSUserBean> selectByParams(HashMap<String, Object> params) {
		List<SYSUserBean> userList = userMapper.selectByParams(params);
		return userList;
	}
	
	/**
	 * 用户登录
	 */
	@Override
	public HashMap<String, Object> getRegInfo(String loginName,String password,String token) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		//获取注册信息
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("loginName",loginName);
		params.put("password",password);
		List<SYSUserBean> userList = userMapper.selectByParams(params);
		
		if(!userList.isEmpty()){
			if(userList.size()>1){
				resultMap.put("msg", "数据异常（返回多条查询结果），请联系管理员！");
				resultMap.put("status",Common_Status.Common_Status_400);
			}else if(userList.size()==1){
				SYSUserBean userBean=userList.get(0);
				resultMap.put("msg","登录成功");
				resultMap.put("status",Common_Status.Common_Status_200);
				resultMap.put("result", userBean);
			}
		}else{
			resultMap.put("msg", "用户名或者密码错误！");
			resultMap.put("status",Common_Status.Common_Status_301);
		}
		return resultMap;
	}
	/**
	 * 注册
	 */
	@Override
	public HashMap<String, Object> reg(String userName,String tel,String loginName, String password) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		SYSUserBean bean = new SYSUserBean();
		bean.setLoginname(loginName);
		bean.setPassword(password);
		bean.setStatus("1");
		bean.setRegtime(new Date());
		bean.setUserName(userName);

		/**
		 * 生成HASHCODE
		 */
		List<String> list = new ArrayList<String>();
		list.add(userName);
		list.add(loginName);
		list.add(password);
		list.add("1");
		String code = Util.getHashcode(list);
		bean.setHashcode(code);
		
		int rs = userMapper.insert(bean);
		if(rs>0){
			resultMap.put("msg","注册成功！");
			resultMap.put("status",Common_Status.Common_Status_200);
		}else{
			resultMap.put("msg","服务异常！");
			resultMap.put("status",Common_Status.Common_Status_400);
		}
		return resultMap;
	}
	/**
	 * 修改注册信息
	 */
	@Override
	public HashMap<String, Object> eidtRegInfo(Long userId,String userName,String idcard,String tel,String email,String password,String token,String status) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		SYSUserBean bean = userMapper.selectByPrimaryKey(userId);
		if(bean==null){
			resultMap.put("msg","根据userId["+userId+"]未找到相关信息！");
			resultMap.put("status",Common_Status.Common_Status_NULL);
			return resultMap;
		}
		
		bean.setUserName(userName);
		bean.setTel(tel);
		bean.setEmail(email);
		bean.setIdcard(idcard);
		bean.setToken(token);
		bean.setPassword(password);
		bean.setStatus(status);
		bean.setUpdatetime(new Date());
		
		/**
		 * 生成HASHCODE
		 */
		List<String> list = new ArrayList<String>();
		list.add(userName);
		list.add(bean.getLoginname());
		list.add(password);
		list.add(status);
		String code = Util.getHashcode(list);
		bean.setHashcode(code);
		
		
		int rs = userMapper.updateByPrimaryKeySelective(bean);
		if(rs>0){
			resultMap.put("msg","修改成功！");
			resultMap.put("status",Common_Status.Common_Status_200);
		}else{
			resultMap.put("msg","服务异常！");
			resultMap.put("status",Common_Status.Common_Status_400);
		}
		return resultMap;
	}

}
