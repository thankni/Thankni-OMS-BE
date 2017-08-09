package com.ldsmsoft.framework.dao.mybatis.dao;

import com.ldsmsoft.framework.dao.mybatis.model.ProductBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface ProductBeanMapper {
    int deleteByPrimaryKey(BigDecimal productId);

    int insert(ProductBean record);

    int insertSelective(ProductBean record);

    ProductBean selectByPrimaryKey(BigDecimal productId);

    int updateByPrimaryKeySelective(ProductBean record);

    int updateByPrimaryKey(ProductBean record);
    

    List<ProductBean> selectByParams(HashMap<String,Object> params);
}