<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
%>
<div class="navbar-fixed-bottom" style="background-color:#eee;height:40px;padding:0px 0px">
	<div style="text-align: center;padding-top:10px;color:black">
		
		推荐使用谷歌浏览器，下载：<a style="color:#A4CCEA" href="<%=path%>/file/download.do?filename=chrome_54_x86_x64.rar">Chrome谷歌浏览器</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		© 驻马店市数据交换平台   All rights reserved.
	</div>
</div>
