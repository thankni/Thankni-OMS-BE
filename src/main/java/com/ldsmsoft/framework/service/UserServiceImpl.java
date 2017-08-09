package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.UserBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.AC01ViewBean;
import com.ldsmsoft.framework.dao.mybatis.model.UserBean;
import com.ldsmsoft.framework.util.DateUtil;
import com.ldsmsoft.framework.util.MD5Util;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserBeanMapper userMapper;
	
	@Autowired
	private PersonService personService;
	
	/**
	 * 根据所传参数，查询用户注册信息
	 */
	@Override
	public List<UserBean> selectByParams(HashMap<String, Object> params) {
		List<UserBean> userList = userMapper.selectByParams(params);
		return userList;
	}
	
	/**
	 * 根据bean，查询用户注册信息
	 */
	@Override
	public UserBean selectByUserBean(UserBean bean) {
		UserBean user = userMapper.selectByUserBean(bean);
		return user;
	}
	
	/**
	 * 用户注册
	 */
	@Override
	public HashMap<String, Object> regUser(String name,String idcard,String icno,String tel,String password) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		
		UserBean user = new UserBean();
		
		//查询注册信息是否已经存在
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("idcard",idcard);
		params.put("icno",icno);
		List<UserBean> userList = userMapper.selectByParams(params);
		
		if(userList.isEmpty()){
			try{
				HashMap<String,Object> param = new HashMap<String,Object>();
				param.put("icno", icno);
				param.put("sfzhm", idcard);
			   //根据身份证号获取个人信息
			   List<AC01ViewBean> ac01List = personService.getPersonByParam(param);
			   if(ac01List.isEmpty()){
					resultMap.put("message", "该身份证号码和社保卡号关联的用户信息不存在或者已经失效，请核实！");
					resultMap.put("resultType","error");
			   }else{
				   for (AC01ViewBean ac01ViewBean : ac01List) {
					   if(ac01ViewBean!=null){
						   String xm = ac01ViewBean.getXm();
						   if(!xm.isEmpty() && name.equals(xm)){
									
								//根据当前时间生成userId
								Long userid = DateUtil.getCurrentDateTime().getTime();
								user.setUserid(userid);
								
								user.setName(name);
								user.setIdcard(idcard);
								user.setIcno(icno);
								user.setTel(tel);
								user.setRegtime(DateUtil.getCurrentDateTime());
								user.setGrbh(ac01ViewBean.getGrbh());
								user.setUpdatetime(DateUtil.getCurrentDateTime());
								
								//密码加密
								password=MD5Util.md5Encode(password);
								user.setPassword(password);
								
								
								//注册（insert）
								userMapper.insert(user);
								
								resultMap.put("message", "注册成功！");
								resultMap.put("resultType", "success");
							}else{
								resultMap.put("message", "所填姓名与身份证号和社保卡号关联所查到用户的姓名不符，请核实！");
								resultMap.put("resultType", "error");
							}
					   }
				   }
				  
			   }
			}catch(Exception e){
				resultMap.put("message", "发生异常，异常信息为："+e.getCause().getMessage());
				resultMap.put("resultType","error");
			}
		}else{
			resultMap.put("message", "该身份证号码和社保卡号的注册信息已存在，不需要重复注册！");
			resultMap.put("resultType","error");
		}
		return resultMap;
	}
	
	/**
	 * 用户登录
	 */
	@Override
	public HashMap<String, Object> getRegInfo(String idcard,String password) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		//获取注册信息
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("idcard",idcard);
		params.put("password",password);
		List<UserBean> userList = userMapper.selectByParams(params);
		
		if(!userList.isEmpty()){
			if(userList.size()>1){
				resultMap.put("message", "数据异常（返回多条查询结果），请联系管理员！");
				resultMap.put("resultType", "error");
			}else if(userList.size()==1){
				UserBean userBean=userList.get(0);
				resultMap.put("resultData", userBean);
				resultMap.put("resultType", "success");
			}
		}else{
			resultMap.put("message", "用户名或者密码错误！");
			resultMap.put("resultType", "error");
			
		}
		return resultMap;
	}
	
	/**
	 * 修改注册信息
	 */
	@Override
	public HashMap<String, Object> eidtRegInfo(UserBean bean) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		
		userMapper.updateByPrimaryKeySelective(bean);
		
		return resultMap;
	}

}
