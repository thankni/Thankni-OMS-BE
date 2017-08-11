package com.ldsmsoft.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.BindInfoBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.AC01ViewBean;
import com.ldsmsoft.framework.dao.mybatis.model.BindInfoBean;
import com.ldsmsoft.framework.util.DateUtil;
import com.ldsmsoft.framework.util.Util;

@Service("BindService")
public class BindServiceImpl implements BindService{

	@Autowired
	private BindInfoBeanMapper bindMapper;
	
	@Autowired
	private PersonService personService;
	
	/**
	 * 根据所传参数，查询用户绑定信息
	 */
	@Override
	public List<BindInfoBean> selectByParams(HashMap<String, Object> params) {
		List<BindInfoBean> beanList = bindMapper.selectByParams(params);
		return beanList;
	}
	
	/**
	 * 用户绑定信息查询
	 */
	@Override
	public HashMap<String, Object> getBindInfo(String clientId,String clientUserid) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();

		//查询绑定信息是否已经存在且有效
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("clientId",clientId);
		params.put("clientUserid",clientUserid);
		params.put("isvalid", 1);
		List<BindInfoBean> infoList = bindMapper.selectByParams(params);
		
		if(!infoList.isEmpty()){
			resultMap.put("resultData", infoList);
			resultMap.put("resultType", "success");
		}else{
			resultMap.put("message", "该用户未绑定或者绑定信息无效，请重新绑定！");
			resultMap.put("resultType","error");
		}
				  
		return resultMap;
	}
	
	/**
	 * 绑定用户信息
	 */
	@Override
	public HashMap<String, Object> bindUser(String clientId,String clientUserid,String sfzhm,String icno,String name) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		
		BindInfoBean bean = new BindInfoBean();
		
		//查询绑定信息是否已经存在且有效
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("clientId",clientId);
		params.put("clientUserid",clientUserid);
		params.put("isvalid", 1);
		List<BindInfoBean> infoList = bindMapper.selectByParams(params);
		
		if(infoList.isEmpty()){
			try{
				HashMap<String,Object> param = new HashMap<String,Object>();
				param.put("icno", icno);
				param.put("sfzhm", sfzhm);
			   //根据身份证号获取个人信息
			   List<AC01ViewBean> ac01List = personService.getPersonByParam(param);
			   if(ac01List.isEmpty()){
				   resultMap.put("message", "该身份证号码和社保卡号关联的用户信息不存在或者已经失效，请核实！");
				   resultMap.put("resultType","error");
			   }else{
				   AC01ViewBean ac01ViewBean = ac01List.get(0);
				   if(ac01ViewBean!=null){
					   String xm = ac01ViewBean.getXm();
					   
					   if(!Util.isEmpty(name)){
						   
						   if(xm.equals(name)){
							   
							   /*Long grbh = ac01ViewBean.getGrbh();
							   if(grbh!=null){*/
								   
								   //根据当前时间生成bindId
								   Long bindId = DateUtil.getCurrentDateTime().getTime();
								   bean.setBindId(bindId);
								   
								   //bean.setGrbh(grbh);
								   bean.setBindDate(DateUtil.getCurrentDateTime());
								   bean.setClientId(clientId);
								   bean.setClientUserid(clientUserid);
								   bean.setIsvalid((short) 1);
								   bean.setName(name);
								   bean.setIdcard(sfzhm);
								   bean.setIcno(icno);
								   
								   //用户信息绑定（insert）
								   bindMapper.insert(bean);
								   
								   //绑定成功返回个姓名，以便其他平台在调用的时候不需要再另外去查该绑定用户的姓名
								   resultMap.put("name", name);
								   resultMap.put("message", "绑定成功！");
								   resultMap.put("resultType", "success");
							   /*}else{
								   resultMap.put("message", "用户不存在，请核实身份证号和社保卡号！");
								   resultMap.put("resultType", "error");
							   }*/
						   }else{
							   resultMap.put("message", "用户不存在，请核实身份证号、社保卡号和姓名！");
							   resultMap.put("resultType", "error");
						   }
					   }else{
						   resultMap.put("message", "姓名不能为空！");
						   resultMap.put("resultType", "error");
					   }
				   }
				  
			   }
			}catch(Exception e){
				resultMap.put("message", "发生异常，异常信息为："+e.getCause().getMessage());
				resultMap.put("resultType","error");
			}
		}else{
			resultMap.put("message", "该客户端账号已绑定且绑定信息有效，不需要再次绑定！");
			resultMap.put("resultType","error");
		}
		return resultMap;
	}

	/**
	 * 解绑用户信息
	 */
	@Override
	public HashMap<String, Object> unbindUser(Long bindId) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		
		//查询绑定信息是否已经存在
		BindInfoBean bindBean = bindMapper.selectByPrimaryKey(bindId);
		
		if(bindBean!=null){
			if(("1").equals(bindBean.getIsvalid().toString())){
				
				BindInfoBean bean = new BindInfoBean();
				//解绑做的是软删除，保留绑定和解绑信息，以便后用
				bean.setIsvalid((short)0);
				bean.setBindId(bindBean.getBindId());
				bean.setUnbindDate(DateUtil.getCurrentDateTime());
				
				bindMapper.updateByPrimaryKeySelective(bean);
				
				resultMap.put("message", "解绑成功！");
				resultMap.put("resultType","success");
			}else{
				resultMap.put("message", "已解除绑定，请不要重复操作！");
				resultMap.put("resultType","error");
			}
		}else{
			resultMap.put("message", "未绑定或者已解除绑定！");
			resultMap.put("resultType","error");
		}
		return resultMap;
	}
}
