<%@page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("UTF-8");
	String charts = (String) request.getParameter("charts") == null? "": (String) request.getParameter("charts");
	String url="http://localhost:8080";
	
	String clientId = "PINGAN";
	String clientSecret = "PINGAN411";
	//String sfzhm = "412801197612220013";
	String sfzhm = "412801195510040813";
	String icno = "Q50757609";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<title>数据交换平台接口客户端</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/styles/bootstrap.min.css" media="screen">
	<style type="text/css">
		.jumbotron{
			background-color:#a4ccea;
			color:white;
		}
		.panel-body{
			width:100%;
			word-break:break-all;
		}
		font{
			color:#27AE60;
		}
		body{
			padding-top:50px;
		}
	</style>
	<script src="<%=path%>/scripts/jquery-1.9.1-min.js"></script>
	<script src="<%=path%>/scripts/jquery.validate.min.js"></script>
	<script src="<%=path%>/scripts/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
		    $("#submitForm").click(function(){
				var str = "<table class='table table-striped'>";		    	
				var sfzhm = $("#sfzhm").val();
				var xzlx = $("#xzlx").val();
				var icno = $("#icno").val();
				if(sfzhm==""){
					str+="<tr style='color:red;font-size:15px'><td>ERROR：</td><td>身份证号码不能为空</td></table>"
					$("#resultList").html(str);
					return;
				}
				var url = "/ssmweb/services/DE1002/"+sfzhm+"/"+xzlx+"/"+icno
				$.ajax({
					type:"GET",
					url:url,
					dataType:"json",
					success:function(data){
						var type = data.resultType;
						var resultData = data.resultData[0];

						if(type=="success"){
							for ( var key in resultData) {
								if(resultData[key]!=null){
									str+="<tr><td>"+key+"</td><td>"+resultData[key]+"</td>"
								}
							}
							str+="</table>"
							$("#resultList").html(str);
						}else{
							str+="<tr style='color:red;font-size:15px'><td>ERROR：</td><td>"+data.message+"</td></table>"
							$("#resultList").html(str);
						}
					},
					error:function(data){
						str+="<tr style='color:red;font-size:15px'><td>ERROR：</td><td>"+data.message+"</td></table>"
						$("#resultList").html(str);
					}
					
				})
		    });
		});
	</script>
</head>
<body>
<jsp:include page="/WEB-INF/pages/user/header.jsp">
	<jsp:param value="api" name="menu"/>
</jsp:include>
<div class="jumbotron">
  <div class="container">
    <h2 align="center">数据交换平台接口调用示例</h2>
  </div>
</div>
<div class="container bs-docs-container">
	<div class="rows">
		<div class="col-md-9" role="main">
		
			<div class="panel panel-default">
				<div class="panel-heading">卡状态查询</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE1004/clientId/clientSecret/icno/sfzhm.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>
					<p><font>参数icno：</font>社保卡号</p>
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE1004/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json"><%=url%>/ssmweb/services/DE1004/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading"><h3 style="color:red">社保卡临时挂失(点击请慎重)</h3></div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE1005/clientId/clientSecret/icno/sfzhm.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数icno：</font>社保卡号</p>
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE1005/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json"><%=url%>/ssmweb/services/DE1005/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading"><h3 style="color:red">社保卡解除挂失</h3></div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE1006/clientId/clientSecret/icno/sfzhm.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数icno：</font>社保卡号</p>
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE1006/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json"><%=url%>/ssmweb/services/DE1006/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json</a>
				</div>
			</div>	
			<div class="panel panel-default">
				<div class="panel-heading"><h3 style="color:red">制卡进度查询</h3></div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE1007/clientId/clientSecret/icno/sfzhm.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数icno：</font>社保卡号</p>
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE1007/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json"><%=url%>/ssmweb/services/DE1007/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json</a>
				</div>
			</div>				
			<a name="tag_1"></a> 			
			<div class="panel panel-default">
				<div class="panel-heading">1、获取用户信息</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE1001/clientId/clientSecret/icno/sfzhm.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数icno：</font>社保卡号</p>
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE1001/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json"><%=url%>/ssmweb/services/DE1001/<%=clientId%>/<%=clientSecret%>/<%=icno%>/<%=sfzhm%>.json</a>
				</div>
			</div>

			<a name="tag_2"></a> 			
			<div class="panel panel-default">
				<div class="panel-heading">2、获取用户参保缴费信息</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE1002/clientId/clientSecret/sfzhm/xzlx/icno.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<p><font>参数xzlx：</font>医疗保险2，养老保险1</p>
					<p><font>参数icno：</font>社保卡号</p>
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE1002/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/2/<%=icno%>.json"><%=url%>/ssmweb/services/DE1002/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/2/<%=icno%>.json</a>
				</div>
			</div>
			<a name="tag_3"></a> 			
			<div class="panel panel-default">
				<div class="panel-heading">3、获取用户参保缴费明细</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE1003/clientId/clientSecret/sfzhm/xzlx/icno.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<p><font>参数xzlx：</font>医疗保险2，养老保险1</p>
					<p><font>参数icno：</font>社保卡号</p>
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE1003/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/2/<%=icno%>.json"><%=url%>/ssmweb/services/DE1003/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/2/<%=icno%>.json</a>
				</div>
			</div>
			<a name="tag_4"></a> 
			<div class="panel panel-default">
				<div class="panel-heading">4、获取用户信息及其医疗消费</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE2001/clientId/clientSecret/sfzhm/icno/beginTime/endTime.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<p><font>参数icno：</font>社保卡号</p>
					<p><font>参数beginTime：</font>开始时间，格式：yyyyMMddhhmmss</p>
					<p><font>参数endTime：</font>结束时间，格式：yyyyMMddhhmmss</p>
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE2001/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>/20150101000000/20160601000000.json"><%=url%>/ssmweb/services/DE2001/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>/20150101000000/20160601000000.json</a>
				</div>
			</div>
			<a name="tag_5"></a> 			
			<div class="panel panel-default">
				<div class="panel-heading">5、获取用户信息及其医疗消费</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE2001/clientId/clientSecret/sfzhm/icno.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>
					<p><font>参数icno：</font>社保卡号</p>
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE2001/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>.json"><%=url%>/ssmweb/services/DE2001/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>.json</a>
				</div>
			</div>
			<a name="tag_6"></a> 			
			<div class="panel panel-default">
				<div class="panel-heading">6、获取用户在院信息</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE3001/clientId/clientSecret/sfzhm/icno.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<p><font>参数icno：</font>社保卡号</p>
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE3001/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>.json"><%=url%>/ssmweb/services/DE3001/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>.json</a>
				</div>
			</div>
			<a name="tag_7"></a> 			
			<div class="panel panel-default">
				<div class="panel-heading">7、获取用户在院的消费单据信息</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE3002/clientId/clientSecret/sfzhm/icno.json</p>
					<p><font>参数clientId：</font>授权账号</p>
					<p><font>参数clientSecret：</font>授权码</p>					
					<p><font>参数sfzhm：</font>15位或者18位合法身份证号（字符串）</p>	
					<p><font>参数icno：</font>社保卡号</p>
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE3002/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>.json"><%=url%>/ssmweb/services/DE3002/<%=clientId%>/<%=clientSecret%>/<%=sfzhm%>/<%=icno%>.json</a>
				</div>
			</div>
			<a name="tag_8"></a> 			
			<div class="panel panel-default">
				<div class="panel-heading">8、获取驻马店人社局网站的通知公告</div>
				<div class="panel-body">
					<p><font>url格式：</font><%=url%>/ssmweb/services/DE4001.json</p>
					<font>示例：</font><a href="<%=url%>/ssmweb/services/DE4001.json"><%=url%>/ssmweb/services/DE4001.json</a>
				</div>
			</div>	
			<a name="tag_Ajax"></a> 					
			<div class="panel panel-default">
				<div class="panel-heading">一个简单Ajax请求的例子</div>
				<div class="panel-body">
					<form class="form">
					    <div class="form-group">
					        <label for="exampleInputName2">授权账户：(<%=clientId%>)</label>
					        <input type="text" class="form-control" id="clientId" placeholder="授权账户">
					    </div>
					    <div class="form-group">
					        <label for="exampleInputName2">授权码：(<%=clientSecret%>)</label>
					        <input type="text" class="form-control" id="clientSecret" placeholder="授权码">
					    </div>
					    <div class="form-group">
					        <label for="exampleInputName2">身份证号码：(<%=sfzhm%>)</label>
					        <input type="text" class="form-control" id="sfzhm" placeholder="身份证号码">
					    </div>
					    <div class="form-group">
					        <label for="exampleInputName2">社保卡号：(<%=icno%>)</label>
					        <input type="text" class="form-control" id="icno" placeholder="社保卡号">
					    </div>
					    <div class="form-group">
					    	<label for="exampleInputName2">险种类型：</label>
					    	<select class="form-control" id="xzlx">
					    		<option value="2">医疗保险</option>
					    		<option value="1">养老保险</option>
					    		<option value="3">工伤保险</option>
					    		<option value="4">生育保险</option>
					    		<option value="5">失业保险</option>
					    	</select>
					    </div>
					    <button id="submitForm" type="button" class="btn btn-success">查询</button>
					</form>
					<span id="resultList">
					</span>
				</div>
				
			</div>

		</div><!-- end col-md-9 -->
		<div class="col-md-3" role="complementary">
			<a href="#tag_1" style="text-decoration:none">		
				<div class="panel panel-default">
					<div class="panel-heading">1、获取用户信息</div>
				</div>
			</a> 				
			<a href="#tag_2" style="text-decoration:none">
				<div class="panel panel-default">
					<div class="panel-heading">2、获取用户参保缴费信息</div>
				</div>			
			</a> 
			<a href="#tag_3" style="text-decoration:none">			
				<div class="panel panel-default">
					<div class="panel-heading">3、获取用户参保缴费明细</div>
				</div>
			</a> 				
			<a href="#tag_4" style="text-decoration:none">			
				<div class="panel panel-default">
					<div class="panel-heading">4、获取用户医疗消费信息</div>
				</div>
			</a> 
			<a href="#tag_5" style="text-decoration:none">			
				<div class="panel panel-default">
					<div class="panel-heading">5、获取用户医疗消费信息</div>
				</div>
			</a> 
			<a href="#tag_6" style="text-decoration:none">			
				<div class="panel panel-default">
					<div class="panel-heading">6、获取用户在院信息</div>
				</div>
			</a> 
			<a href="#tag_7" style="text-decoration:none">			
				<div class="panel panel-default">
					<div class="panel-heading">7、获取用户在院的消费单据信息</div>
				</div>
			</a>
			<a href="#tag_8" style="text-decoration:none">			
				<div class="panel panel-default">
					<div class="panel-heading">8、获取驻马店人社局网站的通知公告</div>
				</div>
			</a>  
			<a href="#tag_Ajax" style="text-decoration:none">			
				<div class="panel panel-default">
					<div class="panel-heading">一个简单Ajax请求的例子</div>
				</div>
			</a> 															
		</div><!-- end col-md-9 -->
	</div>

</div>	
</body>
</html>
