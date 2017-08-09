package com.common.webconfig;

import java.io.IOException;
import java.util.logging.LogRecord;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ±àÂë¹ýÂËÆ÷
 * 
 * @author wengsh
 * @2012-3-19
 */
public class CharacterEncodingFilter implements Filter {

	private ServletContext sc;
	private FilterConfig fc;

	public void destroy() {
		this.sc = null;
		this.fc = null;

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String charsetEncoding = fc.getInitParameter("encoding");
		request.setCharacterEncoding(charsetEncoding);// ¹ý¶ÉÆ÷±àÂë
		response.setCharacterEncoding(charsetEncoding);

		chain.doFilter(request, response);

	}

	public void init(FilterConfig fc) throws ServletException {
		this.fc = fc;
		this.sc = fc.getServletContext();
	}

	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

}
