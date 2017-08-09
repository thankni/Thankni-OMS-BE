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
	<title>数据交换平台接口客户端-登录</title>
	<link rel="stylesheet" href="<%=path%>/styles/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path%>/styles/bootstrapValidator.min.css">
	<style type="text/css">
		body{
			background-color:#a4ccea;
			color:white;
		}
		#skipReg {
			color:white;
			font-size:18px;
		}
		.jumbotron{
			color:black;
		}
		
		/*ie浏览器版本提示信息*/
		.ie-tips {
		  display: none;
		  position: relative;
		  height: 30px;
		  line-height: 30px;
		  background: #f9d272;
		  color: #f00;
		  font-size: 14px;
		  text-align: center;
		}
		.ie-tips b {
		  font-weight: bold;
		}
		.ie-tips .js-tip-close {
		  position: absolute;
		  top: 0;
		  right: 20px;
		  cursor: pointer;
		}
		/*ie浏览器版本提示信息*/
	</style>

</head>
<body>
<div class="ie-tips">您使用的浏览器版本过旧，为了更好的访问体验，请升级浏览器至<b>IE8以上</b>，或者使用推荐的浏览器<a href="<%=path%>/file/download.do?filename=chrome_54_x86_x64.rar">Chrome谷歌浏览器</a><a href="#" class="js-tip-close" onClick="colseTip()">关闭</a></div>
<div class="jumbotron">
  <div class="container">
    <h2 align="center"><strong>驻马店市数据交换平台</strong></h2>
  </div>
</div>
<div class="container bs-docs-container">
	<div class="row">
		<section>
		<div class="col-lg-8 col-lg-offset-2">
			<div class="page-header">
				<h2>登录</h2>
			</div>
			<form id="regForm" class="form-horizontal bv-form" action="<%=path%>/user/loginSubmit.do" method="post">
				<div class="form-group">
				    <label class="col-lg-2 control-label">用户账号</label>
				    <div class="col-lg-9">
				    	<input type="text" class="form-control" id="idcard" name="idcard" value="412801195510040813" placeholder="身份证号码" autofocus>
					</div>
				</div>
				<div class="form-group">
				    <label class="col-lg-2 control-label">登录密码</label>
				    <div class="col-lg-9">
				    	<input type="text" class="form-control" id="password" name="password" placeholder="登录密码" >
					</div>
				</div>
                <div class="form-group form-horizontal">
                    <label class="col-lg-2 control-label " id="captchaOperation"></label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control " name="captcha" placeholder="验证码（请输入两个数字的运算结果）"/>
                    </div>
                </div><br /><br />
				<div class="form-group" >
					<div class="col-lg-9 col-lg-offset-2">
						<button id="login" type="button" class="btn btn-default col-lg-5">登 录</button>
						<a id="skipReg" class="pull-right" href="<%=path%>/user/regIndex.do">没有账号？去注册</a>
					</div>
				</div>				
			</form>
		</div><!-- end col-lg-8 -->

		</section>
	</div>
	<div class="col-lg-8 col-lg-offset-2">
		<div id="div_showMessage" class="alert alert-warning alert-dismissible" role="error" hidden=hidden>
		    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		  		<span aria-hidden="true">&times;</span>
		    </button>
		    <strong>登录失败！&nbsp;&nbsp;&nbsp;</strong> ${message }
		</div>
	</div>
<jsp:include page="bottom.jsp"></jsp:include>
</div>
<script src="<%=path%>/scripts/jquery-1.9.1-min.js"></script>
<script src="<%=path%>/scripts/jquery.validate.min.js"></script>
<script src="<%=path%>/scripts/bootstrap.min.js"></script>
<script src="<%=path%>/scripts/bootstrapValidator.min.js"></script>
<script src="<%=path%>/scripts/commonUtil.js"></script>
<script src="<%=path%>/scripts/zh_CN.js"></script>
<script src="<%=path%>/scripts/md5.js"></script>
<script type="text/javascript">
//关闭页面顶部提示语
function colseTip(){
	$(".ie-tips").hide();
}

$(function(){
	
	//ie版本判断，过低则提示升级或使用推荐浏览器
	if(navigator.appVersion.indexOf(";")>=0) {
	    var b_version = navigator.appVersion.split(";");
	    if (b_version != "undefined") {
	        var trim_version = b_version[1].replace(/[ ]/g, "");
	        if ((navigator.appName == "Microsoft Internet Explorer" && trim_version == "MSIE5.0") ||
	        	(navigator.appName == "Microsoft Internet Explorer" && trim_version == "MSIE6.0") ||
	            (navigator.appName == "Microsoft Internet Explorer" && trim_version == "MSIE7.0")) {
	            $(".ie-tips").show();
	        }
	        else {
	        	//$(".ie-tips").show();
	            $(".ie-tips").hide();
	        }
	    }
	}

	var type = '${type}';
	if(type=='error'){
		var message = '${message}';
		$("#div_showMessage").show();
	}
	
	//制作登录验证码
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    function generateCaptcha() {
        $('#captchaOperation').html([randomNumber(1, 50), '+', randomNumber(1, 50), '='].join(' '));
    };
    generateCaptcha();
    
    $("#login").on("click", function(){
    	 //表单校验
    	var bootstrapValidator = $("#regForm").data('bootstrapValidator');
    	bootstrapValidator.validate();
 
    	if(bootstrapValidator.isValid()){ 
    		
    		var pwd = $("#password").val();
    		//密码加密
    		var enPwd = hex_md5($("#password").val());
    		$("#password").val(enPwd);
    		//表单提交
    		document.getElementById("regForm").submit();
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
            idcard: {
                validators: {
                    notEmpty: {
                        message: '请输入登录账号'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入登录密码'
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
});
</script>	
</body>
</html>
