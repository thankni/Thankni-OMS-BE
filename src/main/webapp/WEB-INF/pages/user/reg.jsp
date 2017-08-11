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
	<title>数据交换平台接口客户端-注册</title>
	<link rel="stylesheet" href="<%=path%>/styles/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path%>/styles/bootstrapValidator.min.css">
	<style type="text/css">
		.jumbotron{
			background-color:#00D1E3;
			color:white;
		}
		.panel-body{
			width:100%;
			word-break:break-all;
		}
		a{
			font-size:18px;
		}

	</style>

</head>
<body>
<div class="container bs-docs-container">
	<div class="row">
		<section>
		<div class="col-lg-8 col-lg-offset-2">
			<div class="page-header">
				<h2>注册</h2>
			</div>
			<form id="regForm" class="form-horizontal bv-form">
				<div class="form-group">
				    <label class="col-lg-3 control-label">姓名</label>
				    <div class="col-lg-5">
					    <input type="text" class="form-control" id="name" name="name" placeholder="姓名" autofocus>
				    </div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">身份证号码</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="idcard" name="idcard" placeholder="身份证号码">
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">社保卡号</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="icno" name="icno" placeholder="社保卡号">
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">手机</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="tel" name="tel" placeholder="手机号">
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
				    	<input type="text" class="form-control" id="identifycode" name="identifycode" placeholder="短信验证码">
					</div>
				</div>				
				<div class="form-group">
				    <label class="col-lg-3 control-label">密码</label>
				    <div class="col-lg-5">
				    	<input type="text" class="form-control" id="password" name="password" placeholder="密码">
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-3 control-label">密码确认</label>
				    <div class="col-lg-5">
				   		<input type="text" class="form-control" id="repassword" name="repassword" placeholder="密码确认">
					</div>
				</div>	
				<div class="form-group">
                    <label class="col-lg-3 control-label " id="captchaOperation"></label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control " id="captcha" name="captcha" placeholder="验证码（请输入两个数字的运算结果）"/>
                    </div>
                </div><br />
				<div class="form-group" >
					<div class="col-lg-9 col-lg-offset-3">
						<button id="submit" type="button" class="btn btn-success">提交</button>	
						<input id="reset" type="reset" class="btn btn-info" value="重置表单">
						<a class="pull-right" href="<%=path %>/user/login.do">&nbsp;&nbsp;&nbsp;&nbsp;已有账号，去登录</a>
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
	    <div id="message"></div>
	    
	</div>
	</div>
</div>
<script src="<%=path%>/scripts/jquery-1.9.1-min.js"></script>
<script src="<%=path%>/scripts/jquery.validate.min.js"></script>
<script src="<%=path%>/scripts/bootstrap.min.js"></script>
<script src="<%=path%>/scripts/bootstrapValidator.js"></script>
<script src="<%=path%>/scripts/formToJson.js"></script>
<script src="<%=path%>/scripts/commonUtil.js"></script>
<script src="<%=path%>/scripts/zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	//制作验证码
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    function generateCaptcha() {
        $('#captchaOperation').html([randomNumber(1, 50), '+', randomNumber(1, 50), '='].join(' '));
    };
    generateCaptcha();
    
	
});
//发送验证码的按钮60秒后重新发送
var countdown=60; 
function settime(obj) { 
    if (countdown == 0) { 
        obj.removeAttribute("disabled");    
        obj.value="免费获取验证码"; 
        countdown = 60; 
        return;
    } else { 
        obj.setAttribute("disabled", true); 
        obj.value="重新发送(" + countdown + "秒)"; 
        countdown--; 
    } 
	setTimeout(function() { 
    	settime(obj) 
    },1000) 
}

//发送短信验证码
function sendIdentifyCode(obj){
	var url = "<%=path%>/user/sendSMS.do";
	
	var tel = $("#tel").val();
	var myreg = /^1(3|4|5|8)\d{9}$/;
	
	if(tel){
		if(myreg.test(tel)){
			//验证码发送后，按钮禁用60秒
			settime(obj);
			
			$.ajax({
				type:"POST",
				url:url,
				data:{tel:tel},
				dataType:"json",
				success:function(data){
					var result = data.result;
					if(result){
						document.getElementById("msg").innerHTML = "<strong style='color:green'>&nbsp;&nbsp;&nbsp;验证码发送成功！</strong> "
					}else{
						document.getElementById("msg").innerHTML = "<strong style='color:red'>&nbsp;&nbsp;&nbsp;验证码发送失败！</strong> "
					}
				},
				error:function(data){
					document.getElementById("msg").innerHTML = "<strong style='color:red'>&nbsp;&nbsp;&nbsp;验证码发送失败！</strong> "
				}
			})
		}else{
			alert("请输入有效手机号！");
		}
	}else{
		alert("手机号不能为空！");
	}
	
};

$("#reset").on("click", function(){
	document.getElementById("regForm").reset();
});
$("#submit").on("click", function(){
	//表单校验
	var bootstrapValidator = $("#regForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	//校验通过，提交表单
	if(bootstrapValidator.isValid()){
		var regForm=formTojson("regForm");
		//删除不属于bean对象的属性
		delete regForm.repassword;
		delete regForm.identifycode;
		delete regForm.captcha;
		
		var identifyCode = $("#identifycode").val();
		var regInfo=jsonToString(regForm);
		
		var url = "<%=path%>/user/regSubmit.do";
		$.ajax({
			type:"POST",
			url:url,
			data:{user:regInfo,identifyCode:identifyCode},
			dataType:"json",
			success:function(data){
				if(data.resultType=='success'){
						window.location.href="<%=path%>/user/regSuccess.do"
					}else{
						document.getElementById("message").innerHTML = "<strong>注册失败！&nbsp;&nbsp;&nbsp;</strong> "+data.message;
					}
					$("#div_showMessage").show();
				},
				error:function(data){
					document.getElementById("message").innerHTML = "<strong>注册失败！&nbsp;&nbsp;&nbsp;</strong> "+data.message;
					$("#div_showMessage").show();
				}
			})
		}else{
			return;
		};
});

//表单验证
$('#regForm').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	
        name: {
            validators: {
                notEmpty: {
                    message: '请填写姓名'
                },
                stringLength: {
                    min: 2,
                    max: 4,
                    message: '姓名长度必须在2和4位之间'
                },
                regexp: {
                	regexp: /^([\u4E00-\u9FA5])*$/,
                	message: '只能填写中文'
                } 
            }
        },
        idcard: {
            validators: {
                notEmpty: {
                    message: '请输入身份证号码'
                },
                regexp: {
                    regexp: /^[0-9]{17}[xX0-9]{1}$/,
                    message: '请输入有效身份证号码！'
                }
            }
        },
        icno: {
            validators: {
                notEmpty: {
                    message: '请输入社保卡号'
                }
            }
        },
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
        identifycode: {
        	validators: {
        		notEmpty: {
        			message:'请输入短信验证码'
        		}
        	}
        	
        },
        password: {
            validators: {
                notEmpty: {
                    message: '请输入密码'
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9]{8,18}$/,
                    message: '密码长度必须在8和18位之间，且只能由字母、数字组成'
                },
                identical:{
                	field:'repassword',
                	message:'两次密码输入不一致'
                },
                different:{
                	field:'idcard',
                	message:'密码不能和身份证号一样'
                },
                different:{
                	field:'icno',
                	message:'密码不能和社保卡号一样'
                }
            }
        },
        repassword: {
            validators: {
                notEmpty: {
                    message: '请再次输入密码'
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9]{8,18}$/,
                    message: '密码长度必须在8和18位之间，且只能由字母、数字组成'
                },
                identical:{
                	field:'password',
                	message:'两次密码输入不一致'
                },
                different:{
                	field:'idcard',
                	message:'密码不能和身份证号一样'
                },
                different:{
                	field:'icno',
                	message:'密码不能和社保卡号一样'
                }
            }
        },
        captcha: {
            validators: {
                callback: {
                    message: '验证码错误',
                    callback: function(value, validator) {
                        var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
                        return value == sum;
                    }
                }
            }
        }
    }
});
</script>	
</body>
</html>
