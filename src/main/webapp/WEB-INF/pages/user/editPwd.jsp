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
	<title>数据交换平台接口客户端-密码修改</title>
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
				<h2>修改密码</h2>
			</div>
			<form id="validForm" class="form-horizontal bv-form">
				<div class="form-group">
				    <label class="col-lg-3 control-label">手机号</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="tel" name="tel" placeholder="账户绑定的手机号" autofocus>
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label"></label>
				    <div class="col-lg-5">
				    	<input type="button" id="btn" class="btn btn-success"  value="免费获取验证码" onclick="sendIdentifyCode(this)" /><span id="msg"></span>
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
						<button id="next" type="button" class="btn btn-default">提交验证</button>
					</div>
				</div>				
			</form>
						
			<form id="editForm" class="form-horizontal bv-form" style="display:none" action="<%=path%>/user/editReg.do" method="post">
				<div class="form-group">
				    <label class="col-lg-3 control-label">新密码</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="password" name="password" placeholder="填写新密码">
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">密码确认</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="repassword" name="repassword" placeholder="新密码确认">
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
<script src="<%=path%>/scripts/md5.js"></script>
<script type="text/javascript">
$(function(){
});
	var type = '${type}';
	if(type=='error'){
		var message = '${message}';
		$("#div_showMessage").show();
	}

	//填写手机号后，点击发送验证码
	function sendIdentifyCode(obj){
		var tel = $("#tel").val();
		var url = "<%=path%>/user/validateTel.do";
		var myreg = /^1(3|4|5|8)\d{9}$/;
		if(tel){
			if(myreg.test(tel)){
				
				$.ajax({
					type:'POST',
					url:url,
					data:{tel:tel},
					dataType:'json',
					success:function(data){
						var rs = data.valid;
						if(rs){
							sendIdenCode(obj,"msg","<%=path%>/user/sendSMS.do",tel)
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
			alert("请输入手机号1！");
		}
	};


	//手机号验证通过，显示新的元素块，以作填写新密码
	$("#next").on("click", function(){
		//表单校验
		var bootstrapValidator = $("#validForm").data('bootstrapValidator');
		bootstrapValidator.validate();

		if(bootstrapValidator.isValid()){
			var url = "<%=path%>/user/validIdenCode.do";
			var tel = $("#tel").val();
			var idenCode = $("#identifyCode").val();
			if(tel && idenCode){
				$.ajax({
					type:'POST',
					url:url,
					data:{tel:tel,idenCode:idenCode},
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
				alert("请输入短信验证码！");
			}
		}else{
			return;
		};
	 });    
    
    //密码修改表单提交
    $("#save").on("click", function(){
    	//表单校验
    	var bootstrapValidator = $("#validForm").data('bootstrapValidator');
    	bootstrapValidator.validate();
 
    	if(bootstrapValidator.isValid()){
    		var enPwd = hex_md5($("#password").val());
    		$("#password").val(enPwd);
    		$("#repassword").val(enPwd);
			document.getElementById("editForm").submit();
    	}else{
    		return;
    	};
     });
    
  	//手机号验证表单验证
    $('#validForm').bootstrapValidator({
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
            identifyCode_old: {
            	validators:{
            		notEmpty: {
            			message:'请输入短信验证码！'
            		}
            	}
            }
        }
    });
  
    //密码修改表单验证
    $('#editForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入密码！'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]{8,18}$/,
                        message: '密码长度必须在8和18位之间，且只能由字母、数字组成！'
                    },
                    identical:{
                    	field:'repassword',
                    	message:'两次密码输入不一致！'
                    }
                }
            },
            repassword: {
                validators: {
                    notEmpty: {
                        message: '请再次输入密码！'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]{8,18}$/,
                        message: '密码长度必须在8和18位之间，且只能由字母、数字组成！'
                    },
                    identical:{
                    	field:'password',
                    	message:'两次密码输入不一致！'
                    }
                }
            }
        }
    });
</script>	
</body>
</html>
