<%@page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
	String charts = (String) request.getParameter("charts") == null? "": (String) request.getParameter("charts");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<title>数据交换平台接口客户端-修改成功</title>
	<link rel="stylesheet" href="<%=path%>/styles/bootstrap.min.css">
	<style type="text/css">
		body{
			background-color:#eee;
		}
		a{
			color:white;
		}
		.jumbotron{
			background-color:#a4ccea;
			color:white;
		}
	</style>

</head>
<body>
<div class="jumbotron">
  <div class="container">
    <h2 align="center">修改成功！</h2>
    <h3 align="center">登录信息已失效，需要重新登录，<span id="show">5</span>秒后跳转到登录页</h3>
    <h4 align="center"><a href="<%=path%>/user/login.do">如果没有正常进入，点此直接跳转！</a></span></h4>
  </div>
</div>
<script src="<%=path%>/scripts/jquery-1.9.1-min.js"></script>
<script src="<%=path%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	var t=4;//设定跳转的时间 
	setInterval(function(){ 
		if(t==0){ 
			location="<%=path%>/user/login.do"; //#设定跳转的链接地址 
		} 
		document.getElementById("show").innerHTML=""+t+""; // 显示倒计时 
		t--; // 计数器递减 
	},1000); //每隔一秒调用一次方法
	
});
</script>	
</body>
</html>
