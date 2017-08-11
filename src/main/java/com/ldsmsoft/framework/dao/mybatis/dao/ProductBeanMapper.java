package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ProductBean;
import java.util.HashMap;
import java.util.List;

public interface ProductBeanMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(ProductBean record);

    int insertSelective(ProductBean record);

    ProductBean selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(ProductBean record);

    int updateByPrimaryKey(ProductBean record);
    
    List<ProductBean> selectByParams(HashMap<String,Object> params);
}