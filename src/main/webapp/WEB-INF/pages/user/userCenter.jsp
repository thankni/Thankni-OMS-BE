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
	<title>数据交换平台接口客户端-用户中心</title>
	<link rel="stylesheet" href="<%=path%>/styles/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path%>/styles/bootstrapValidator.min.css">
	<style type="text/css">
		body {
			background-color:#a4ccea;
			color:white;
			padding-top: 70px;
		}
		.table {
			background-color:#eee;
			color:black;
		}
		td {
			text-align:center;
			height:50px;
			line-height:50px;
			vertical-align:center;
		}
	</style>

</head>
<body>
<jsp:include page="header.jsp">
	<jsp:param value="center" name="menu"/>
</jsp:include>
<div class="container bs-docs-container">
	<div class="row">
		<section>
		<div class="col-lg-8 col-lg-offset-2">
			<div class="page-header">
				<h2>个人信息</h2>
			</div>
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td>姓名</td>
						<td>${name }</td>
						<td></td>
					</tr>
					<tr>
						<td>用户账号</td>
						<td>${idcard }</td>
						<td></td>
					</tr>
					<tr>
						<td>社保卡号</td>
						<td>${icno }</td>
						<td></td>
					</tr>
					<tr>
						<td>手机</td>
						<td>${tel }</td>
						<td><a class="btn btn-info" href="<%=path%>/user/editTel.do">更换手机</a></td>
					</tr>
					<tr>
						<td>登录密码</td>
						<td>****</td>
						<td><a class="btn btn-info" href="<%=path%>/user/editPwd.do">修改密码</a></td>
					</tr>
				</tbody>
			</table>
			<%-- <form id="regForm" class="form-horizontal bv-form">
				<div class="form-group">
				    <label class="col-lg-3 control-label">姓名</label>
				    <div class="col-lg-5">
				    	<span class="form-control">${name }</span>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">身份证号码</label>
				    <div class="col-lg-5">
				    	<span class="form-control">${idcard }</span>
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">社保卡号</label>
				    <div class="col-lg-5">
				    	<span class="form-control">${icno }</span>
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">手机</label>
				    <div class="col-lg-5">
				    	<span class="form-control">${tel }</span>
					</div>
				</div> 
				<div class="form-group" >
					<div class="col-lg-9 col-lg-offset-3">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="btn btn-info" href="<%=path%>/user/editTel.do">更换手机</a>
					</div>
				</div>	--%>
			</form>
		</div><!-- end col-lg-8 -->

		</section>
	</div>
</div>
<script src="<%=path%>/scripts/jquery-1.9.1-min.js"></script>
<script src="<%=path%>/scripts/jquery.validate.min.js"></script>
<script src="<%=path%>/scripts/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){

});
</script>	
</body>
</html>
