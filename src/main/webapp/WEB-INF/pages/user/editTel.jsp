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
	<title>数据交换平台接口客户端-更换手机号</title>
	<link rel="stylesheet" href="<%=path%>/styles/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path%>/styles/bootstrapValidator.min.css">
	<style type="text/css">
		body{
			background-color:#a4ccea;
			color:white;
			padding-top: 70px;
		}
		a{
			color:white;
		}
		.panel-body{
			width:100%;
			word-break:break-all;
		}

	</style>

</head>
<body>
<jsp:include page="header.jsp" flush="true"/>

<div class="container bs-docs-container">
	<div class="row">
		<section>
		<div class="col-lg-8 col-lg-offset-2">
			<div class="page-header">
				<h2>更换手机号</h2>
			</div>
			<form id="validForm" class="form-horizontal bv-form">
				<div class="form-group">
				    <label class="col-lg-3 control-label">原手机号</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="oldTel" name="oldTel" placeholder="原手机号" autofocus>
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label"></label>
				    <div class="col-lg-5">
				    	<input type="button" id="btn_old" class="btn btn-success"  value="免费获取验证码" onclick="sendCode_old(this)"/><span id="msg_old"></span>
					</div>
				</div>				
				<div class="form-group">
				    <label class="col-lg-3 control-label">短信验证码</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="identifyCode_old" name="identifyCode_old" placeholder="短信验证码">
					</div>
				</div>				
				<div class="form-group" >
					<div class="col-lg-9 col-lg-offset-3">
						<button id="next" type="button" class="btn btn-default">提交验证</button>
					</div>
				</div>				
			</form>
						
			<form id="editForm" class="form-horizontal bv-form" style="display:none" action="<%=path%>/user/editReg.do" method="post">
				<div class="form-group">
				    <label class="col-lg-3 control-label">新手机号</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="tel" name="tel" placeholder="填写新手机号">
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label"></label>
				    <div class="col-lg-5">
				    	<input type="button" id="btn" class="btn btn-success"  value="免费获取验证码" onclick="sendCode(this)"/><span id="msg"></span>
					</div>
				</div>				
				<div class="form-group">
				    <label class="col-lg-3 control-label">短信验证码</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="identifyCode" name="identifyCode" placeholder="短信验证码">
					</div>
				</div>	
				<div class="form-group" >
					<div class="col-lg-9 col-lg-offset-3">
						<button id="save" type="button" class="btn btn-default">保存</button>
					</div>
				</div>					
			</form>
		</div><!-- end col-lg-8 -->

		</section>
	</div>
	<div class="col-lg-8 col-lg-offset-2">
		<div id="div_showMessage" class="alert alert-warning alert-dismissible" role="alert" hidden=hidden>
		    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		  		<span aria-hidden="true">&times;</span>
		    </button>
		    <strong>验证失败！&nbsp;&nbsp;&nbsp;</strong> ${message }
		</div>
	</div>
</div>
<script src="<%=path%>/scripts/jquery-1.9.1-min.js"></script>
<script src="<%=path%>/scripts/jquery.validate.min.js"></script>
<script src="<%=path%>/scripts/bootstrap.min.js"></script>
<script src="<%=path%>/scripts/bootstrapValidator.min.js"></script>
<script src="<%=path%>/scripts/smPersonal.js"></script>
<script type="text/javascript">
$(function(){

});

var type = '${type}';
if(type=='error'){
	var message = '${message}';
	$("#div_showMessage").show();
}

//填写原手机号后，点击发送验证码
function sendCode_old(obj){
	var oldTel = $("#oldTel").val();
	var url = "<%=path%>/user/validateTel.do";
	var myreg = /^1(3|4|5|8)\d{9}$/;
	if(oldTel){
		if(myreg.test(oldTel)){
			//禁用按钮，防止重复提交
			$(this).attr("disabled","true");
			$.ajax({
				type:'POST',
				url:url,
				data:{tel:oldTel},
				dataType:'json',
				success:function(data){
					var rs = data.valid;
					if(rs){
						sendIdenCode(obj,"msg_old","<%=path%>/user/sendSMS.do",oldTel)
					}else{
						alert("请输入有效手机号！");
					}
					
				},
				error:function(data){
					alert("验证失败！");
				}
			})
		}else{
			alert("请输入有效手机号！");
		}
	}else{
		alert("请输入手机号！");
	}
};


//原手机号验证通过，显示新的元素块，以作填写新手机号
$("#next").on("click", function(){
	//表单校验
	var bootstrapValidator = $("#validForm").data('bootstrapValidator');
	bootstrapValidator.validate();

	if(bootstrapValidator.isValid()){
		var url = "<%=path%>/user/validIdenCode.do";
		var oldTel = $("#oldTel").val();
		var idenCode = $("#identifyCode_old").val();
		if(oldTel && idenCode){
			$.ajax({
				type:'POST',
				url:url,
				data:{tel:oldTel,idenCode:idenCode},
				dataType:'json',
				success:function(data){
					var rs = data.valid;
					if(rs){
						$("#editForm").show();
						$("#validForm").hide();
					}else{
						alert("验证码或手机号错误，验证未通过！");
					}
				},
				error:function(data){
					alert("验证未通过！");
				}
			})
		}else{
			return;
		}
	}else{
		return;
	};
 });    

//原手机号验证表单验证
$('#validForm').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        oldTel: {
        	validators: {
        		notEmpty: {
        			message:'请填写手机号'
        		},
                regexp: {
                    regexp: /^1(3|4|5|8)\d{9}$/,
                    message: '请输入有效手机号！'
                }
                
        	}
        },
        identifyCode_old: {
        	validators:{
        		notEmpty: {
        			message:'请填写短信验证码'
        		}
        	}
        }
    }
});

//填写新手机号后，点击发送验证码
function sendCode(obj){
	var tel = $("#tel").val();
	var myreg = /^1(3|4|5|8)\d{9}$/;
	if(tel){
		if(myreg.test(tel)){
			//禁用按钮，防止重复提交
			$(this).attr("disabled","true");
			sendIdenCode(obj,"msg","<%=path%>/user/sendSMS.do",tel)
		}else{
			alert("请输入有效手机号！");
		}
	}else{
		alert("请输入手机号！");
	}
};

//新手机号表单提交
$("#save").on("click", function(){
	//表单校验
	var bootstrapValidator = $("#editForm").data('bootstrapValidator');
	bootstrapValidator.validate();

	if(bootstrapValidator.isValid()){
		var url = "<%=path%>/user/saveTel.do";
		var tel = $("#tel").val();
		var idenCode = $("#identifyCode").val();
		if(tel && idenCode){
			$.ajax({
				type:'POST',
				url:url,
				data:{tel:tel,idenCode:idenCode},
				dataType:'json',
				success:function(data){
					var rs = data.result;
					if(rs){
						alert("手机号更换成功！");
						window.location.href="<%=path%>/user/saveTelSuccess.do";
					}else{
						alert("验证码或手机号错误！");
					}
				},
				error:function(data){
					alert("验证出错，验证码或手机号错误，或者服务异常，请稍后再试！");
				}
			})
		}else{
			alert("请输入短信验证码！");
		}
	}else{
		return;
	};
 });    

//新手机号验证表单验证
$('#editForm').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        tel: {
        	validators: {
        		notEmpty: {
        			message:'请输入手机号！'
        		},
                regexp: {
                    regexp: /^1(3|4|5|8)\d{9}$/,
                    message: '请输入有效手机号！'
                }
                
        	}
        },
        identifyCode: {
        	validators:{
        		notEmpty: {
        			message:'请输入短信验证码！'
        		}
        	}
        }
    }
});


<%-- remote: {//ajax验证
	type:'POST',
	delay:5000,//设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
	url:'<%=path%>/user/validateTel.do',
	data:{
		tel:$("#oldTel").val()
	},
	message:'不是原手机号，请核实'
} --%>
</script>	
</body>
</html>
