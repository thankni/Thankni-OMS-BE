package com.ldsmsoft.framework.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.ProductBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.ProductBean;
import com.ldsmsoft.framework.util.CheckIDCard;
import com.ldsmsoft.framework.util.DateUtil;
import com.ldsmsoft.framework.util.Util;;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
	
	private Long grbh;
	private ProductBeanMapper productMapper;
	
	// Logger instance
    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

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
	
	@Override
	public List<ProductBean> getProductsByParams(String typeid,String page,String pageSize) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("typeId",typeid);
		
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
		List<ProductBean> list = productMapper.selectByParams(map);
		return list;
	}
	
}
