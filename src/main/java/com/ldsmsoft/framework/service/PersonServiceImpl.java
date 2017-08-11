package com.ldsmsoft.framework.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.util.CheckIDCard;
import com.ldsmsoft.framework.util.DateUtil;
import com.ldsmsoft.framework.util.Util;;

@Service("PersonService")
public class PersonServiceImpl implements PersonService {
	
	private Long grbh;
	
	// Logger instance
    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

	@Override
	public Long getGrbhByParam(HashMap<String, Object> param) {

		try{
		   //根据身份证号获取个人编号
		   List<Long> grbhList=ac01ViewBeanMapper.selectGrbhByParam(param);
		   if(grbhList.isEmpty()){
			  logger.info("该人信息不存在！");
		   }else if(grbhList.size()>1){
			   logger.info("该社会保障号码有重复信息存在！");
		   }else{
			   grbh = grbhList.get(0);
		   }
		}catch (Exception e) {
			 logger.info(e.getMessage().toString());
		}
		return grbh;
	}
    /**
     * 根据身份证号和社保卡号获取用户信息
     */
	@Override
	public List<AC01ViewBean> getPersonByParam(HashMap<String, Object> param) {
		List<AC01ViewBean> list=null;
		try{
		   //根据身份证号获取个人信息
		   list=ac01ViewBeanMapper.selectByParam(param);
		}catch (Exception e) {
			 logger.info(e.getMessage().toString());
		}
		return list;
	}
	
    /**
     * 获取用户信息(单条)
     */
	@Override
	public AC01ViewBean getPerByParam(HashMap<String, Object> param) {
		AC01ViewBean bean = null;
		try{
		   //根据身份证号获取个人信息
			List<AC01ViewBean> list=ac01ViewBeanMapper.selectByParam(param);
		   if(list.isEmpty()){
		       logger.info("该人信息不存在！");
		   }else{
			   if(list.size()>1){
				   logger.info("数据异常（返回多条查询结果），请联系管理员！");
				}else if(list.size()==1){
					bean=list.get(0);
				}
		   }
		}catch (Exception e) {
			 logger.info(e.getMessage().toString());
		}
		return bean;
	}
	

	/**
	 * 验证参数是否符合要求
	 */
	@Override
	public String validateParams(String sfzhm,String icno){
		String errorInfo = "";
		try {
			String errorInfo1 = CheckIDCard.IDCardValidate(sfzhm);
			if(("").equals(errorInfo)){
				if(!Util.isEmpty(icno)){
					errorInfo="";
				}else{
					errorInfo="社保卡号不能为空！";
				}
			}else{
				errorInfo = errorInfo1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return errorInfo;
	}
	
	/**
	 * 根据医疗机构名称关键字，进行区县医疗定点的模糊查询 
	 */
	@Override
	public List<KB01Bean> getEpKB01ByParams(String name,String page,String pageSize) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if("ALL".equals(name)){
			map.put("name","");
		}else{
			map.put("name", name);
		}
		
		//页码
		if(!Util.isEmpty(page)){
			map.put("page", Integer.parseInt(page));
		}else{
			map.put("page", 1);
		}
		//每页条数
		if(!Util.isEmpty(pageSize)){
			map.put("pageSize", Integer.parseInt(pageSize));
		}else{
			map.put("pageSize", 10);
		}
		List<KB01Bean> list = kb01BeanMapper.selectEpByParams(map);
		return list;
	}
	
}
