package com.ldsmsoft.framework.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ldsmsoft.framework.dao.mybatis.dao.ProductBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.ProductBean;
import com.ldsmsoft.framework.dao.mybatis.model.TypeBean;
import com.ldsmsoft.framework.util.CheckIDCard;
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
					errorInfo="";
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
	 * 查询信息
	 */
	@Override
	public List<ProductBean> selectByParams(String typeid,String page,String pageSize) {
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
	/**
	 * 查询信息（单条）
	 */
	@Override
	public ProductBean selectById(String productId) {
		return productMapper.selectByPrimaryKey(Long.parseLong(productId));
	}	
	/**
	 * 修改信息
	 */
	@Override
	public HashMap<String, Object> eidt(ProductBean bean) {

		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		
		productMapper.updateByPrimaryKeySelective(bean);
		
		return resultMap;
	}

	
}
