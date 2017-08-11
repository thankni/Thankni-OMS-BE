package com.ldsmsoft.framework;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	public Connection getConn() {
		Connection conn = null;
		DataSource ds = null;
		String jndi="jdbc/oracle";      
		try {
			Context initContext = new InitialContext();
			Context envContext = null;
			try{
			   envContext = (Context) initContext.lookup("java:/comp/env");// 固定，不需要修改
			}catch(Exception e1){
				// 无法通过java:方式获得换用/comp/env的方式
	            try {
	                envContext = (Context) initContext.lookup("/comp/env");
	            } catch (Exception fff) {
	                e1.printStackTrace();
	            }
			}
			ds = (DataSource) envContext.lookup(jndi);
			if (ds != null) {
				try {
					conn = ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (NamingException e) {
			System.out.println("数据源没找到！");
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<String> selectById(int id) throws InstantiationException, IllegalAccessException{
		Connection con = null;
		try {
			con = getConn();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		String sql = "select count(1)* from dual";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
