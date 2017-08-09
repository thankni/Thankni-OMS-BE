package com.ldsmsoft.framework.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ldsmsoft.framework.util.SpringContextUtil;
//@Repository
@SuppressWarnings("unused")
public class SqlDao{
	
    private String tabstr;
    private String colstr;
    private String where;
    
    @SuppressWarnings("rawtypes")
	private List table;
    private Map<String,String> tablecol;
    private String sqlSessionFactoryBeanName;
	private Connection connection;
	private SqlRunner sr;
    
    /*public Connection getConnection()throws AppException
    {
        return DataSourceUtils.getConnection(getDataSource());
    }*/
    
	public void setSqlSessionFactoryBeanName(String sqlSessionFactoryName) {
		this.sqlSessionFactoryBeanName = sqlSessionFactoryName;
	}

	public void getSqlRunner(){
		if(sr==null){
			SqlSessionFactory ssf=SpringContextUtil.getBean(sqlSessionFactoryBeanName);
			sr=new SqlRunner(ssf.openSession().getConnection());
		}
	}
	
	public Map<String, Object> selectOne(String sql, Object... args) throws SQLException{
		getSqlRunner();
		return sr.selectOne(sql, args);
	}
   
	public List<Map<String, Object>> selectAll(String sql, Object... args) throws SQLException {
		getSqlRunner();
		return sr.selectAll(sql, args);
	}   
    	
}
