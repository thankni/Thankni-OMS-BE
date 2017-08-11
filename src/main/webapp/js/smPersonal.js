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
/**
 * 发送短信验证码
 * html代码例子：<span id="sendSmsMsg"><button id="sendSmsBtn" type="button" class="btn btn-success" >发送验证码</button></span>
 * @param obj 
 * @param div_id1 发送验证码是否成功 元素块id
 * @param url ajax提交url
 * @param phoneNum 目标手机号
 */
function sendIdenCode(obj,div_id1,url,phoneNum){
		
	var tel = phoneNum;
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
				document.getElementById(div_id1).innerHTML = "<strong style='color:green'>&nbsp;&nbsp;&nbsp;验证码发送成功！</strong> "
			}else{
				document.getElementById(div_id1).innerHTML = "<strong style='color:red'>&nbsp;&nbsp;&nbsp;验证码发送失败！</strong> "
			}
		},
		error:function(data){
			document.getElementById(div_id1).innerHTML = "<strong style='color:red'>&nbsp;&nbsp;&nbsp;验证码发送失败！</strong> "
		}
	})
		
}