package com.ldsmsoft.framework.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldsmsoft.framework.dao.mybatis.model.UserBean;

/**
 * 对指定路径下的请求进行session的失效验证，如失效则跳转到登录页面
 * @author Administrator
 *
 */
public class RequestFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;  
	        HttpServletResponse response = (HttpServletResponse) resp;  
	          
	        //取到url路径的最后一个路径名
	        String uri = request.getRequestURI();  
	        uri = uri.substring(uri.lastIndexOf("/")+1);  

	        //配置需要校验的uri
	        if("editTel.do".equals(uri)|| "editTel.jsp".equals(uri)|| "editPwd.jsp".equals(uri) || "editPwd.do".equals(uri) || "userCenter.jsp".equals(uri) || "validateTel.do".equals(uri) || "editReg.do".equals(uri) || "logout.do".equals(uri) || "saveTel.do".equals(uri) || "saveTelSuccess.do".equals(uri)|| "saveTel.do".equals(uri)|| "saveTel.do".equals(uri)|| "saveTel.do".equals(uri) ) {  
	            //下面是判断是否有session，也就是用户是否已登录状态；                                                                                                                                          
	            HttpSession session = request.getSession();  
	            UserBean user = (UserBean) session.getAttribute("currentUser");  
	            if(user == null) {  
	            	//打印一下访问被拒绝的文件
	            	System.out.println("URI:" + uri + ">>>>访问被拒绝！");  
	            	response.sendRedirect("loginError.do");  
	            } else {  
	            	chain.doFilter(request, response);  
	            }  
	        } else {    
	        	//所有人都能请求到的URI，放行  
	        	chain.doFilter(request, response);  
	        }     
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
