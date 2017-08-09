/**
 * 产生随机数
 * 
 * @author wul
 * @param number
 *            是定数目以内产生随机数
 */
function rand(number) {
	rnd.today = new Date();
	rnd.seed = rnd.today.getTime();
	function rnd() {
		rnd.seed = (rnd.seed * 9301 + 49297) % 233280;
		return rnd.seed / (233280.0);
	}
	;
	var s = Math.ceil(rnd() * number);
	return s;
};


function updateFile(url, eleId, fileDir, callback, previousFileName) {
	var uploadfile = $("#" + eleId).val();
	if(uploadfile != '') {
		url = url + "?fileDir=" + fileDir + "";
		$.ajaxFileUpload({
			url : url,
			secureuri : false,
			fileElementId : eleId,
			dataType : 'json',
			success : function(data, status) {
				var dataJson = data.msg;
				dataJson = eval("(" + dataJson + ")");
				//var filepath = dataJson.filePath;
				var _fileName = dataJson.fileName;
				callback(_fileName);
			},
			error : function(data, status, e) {
				console.log(e);
			}
		});
	} else {
		callback($("#" + previousFileName).val());
	}
}

/**
 * 图片上传预览
 * 
 * @author wul
 * @param imgFile
 *            上传的图片
 */
var _fileType = "";
function PreviewImage(imgFile) {
	var filextension = imgFile.value.substring(imgFile.value.lastIndexOf("."),
			imgFile.value.length);
	_fileType = filextension;
	filextension = filextension.toLowerCase();
	if ((filextension != '.jpg') && (filextension != '.gif')
			&& (filextension != '.jpeg') && (filextension != '.png')
			&& (filextension != '.bmp')) {
		$.messager.alert("提示", "对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !", "info");
		imgFile.focus();
	} else {
		var path;
		if (document.all)// IE
		{
			imgFile.select();
			path = document.selection.createRange().text;
			document.getElementById("imgPreview").innerHTML = "";
			document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
					+ path + "\")";// 使用滤镜效果
		} else// FF
		{
			path = window.URL.createObjectURL(imgFile.files[0]);// FF 7.0以上
			// path = imgFile.files[0].getAsDataURL();// FF 3.0
			document.getElementById("imgPreview").innerHTML = "<img id='img1' width='120px' height='100px' src='"
					+ path + "'/>";
			// document.getElementById("img1").src = path;
		}
	}
}

/**
 * 验证带小数点的数字
 * 
 * @author wul
 * @param num
 *            验证的数字
 */
function numberCheck(num, message) {
	var re = /^\d+(\.\d+)?$/;
	var chkInfo = re.exec(num) != null;
	if (!chkInfo) {
		$.messager.alert("提示", message, "error");
	}
	return chkInfo;
}


 /**
  * 验证输入是否为中文
  * 
  * @author zyp
  * @param wordStr
  *            输入的字符串
 
 
 function isChn(wordStr){
     var reg = /^[u4E00-u9FA5]+$/;
     if(reg.test(wordStr)){
    	 $.messager.alert("提示","只能输入汉字！！","error");
     }
     return true;
}
 
 /**
  * 验证输入是否只能为英文和数字
  * 
  * @author zyp
  * @param str
  *            输入的字符串
  */
  
/** function isEnAndNum(str){
	 var re;
     re = /[a-zA-Z0-9]{6,16}/;  
	 if (re.test(str)) {
		   return true;  //匹配
	 }else {
		 $.messager.alert("提示","只能输入英文或者数字！！","error");  //不匹配
	 }
	 
 }
 
 /**
  * 检查输入的起止日期是否正确，规则为两个日期的格式正确，且结束如期>=起始日期 
  * 
  * @author zyp
  * @param startDate：起始日期，字符串 
           endDate：结束如期，字符串  
  */
  
 
/** function checkTwoDate( startDate,endDate ) { 
	 if( !isDate(startDate) ) { 
		 $.messager.alert("提示","起始日期不正确!","error"); 
	 return false; 
	 } else if( !isDate(endDate) ) { 
		 $.messager.alert("提示","终止日期不正确!","error"); 
	 return false; 
	 } else if( startDate > endDate ) { 
		 $.messager.alert("提示","起始日期不能大于终止日期!","error"); 
	 return false; 
	 } 
	 return true; 
	 } */
 
 /** function checkValues(value){
	 var checkValue = value;
	 var email = $('email').val();
	 var phoneNum = $('phoneNum').val();
	 var wStr =  $('wStr').val();  
	 var emailRe =  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	 var phoneNumRe = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
	 var wStrRe = /^[u4E00-u9FA5]+$/;
	 var nikeName = /^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/; 
	 switch(checkValue){
	    case email:
	    	if(!emailRe.test(email)){
	    		$.messager.alert("提示信息","请输入正确的邮箱格式!","error");
	    		return;
	    	}break;
	    case phoneNum:
	    	if(!phoneNumRe.exec(phoneNum)){
	    		$.messager.alert("提示信息","请输入正确的手机号码!","error");
	    		return;
	    	}break;
	    case wStr:
	    	if(!wStrRe.test(wStr)){
	    		$.messager.alert("提示信息","只能输入汉字!","error");
	    		return;
	    	}break;
	 
	 }
	 
 }*/
 
 
 
/**
 * 公用验证表单方法
 * 
 * @author wul
 * @param checkControlAttr
 *            控件数组
 * @param checkControlAttr
 *            提示信息数组
 */
function checkFormCommon(checkControlAttr, checkMessagerAttr) {
	for (var i = 0; i < checkControlAttr.length; i++) {
		var ctlVal = $("#" + checkControlAttr[i]).val();
		ctlVal = ctlVal.Trim();
		if ('' == ctlVal || ctlVal.length <= 0) {
			$.messager.alert("提示", checkMessagerAttr[i], "info", function(e) {
				$("#" + checkControlAttr[i]).focus();
			});
			return false;
		}
	}
	return true;
}

/**
 * 重新加载表格
 * 
 * @author wul
 * @param dataGridId
 */
function reloadTableCommon(dataGridId) {
	$('#' + dataGridId).datagrid('reload');
	$('#' + dataGridId).datagrid('clearSelections');
}

/**
 * 去除用户选择的lable和img
 * 
 * @author wul
 * @param obj
 *            控件obj
 * @param prefixname
 *            控件id
 */
function removebnlables(obj, controlId) {
	// 去除控件
	$("[name='" + obj.name + "']").remove();
	_u_sel_auto_val = _u_sel_auto_val.replace(obj.name + ",", "");
}

/**
 * 模糊匹配单选效果
 * 
 * @author wul
 * @param uSelValTemp
 *            用户选择的数值
 * @param $input
 *            控件
 */
function autoEventClickCommonRadio(uSelValTemp, $input) {
	var uSelValArr = uSelValTemp.split(":");
	var uSelVal = uSelValArr[0];
	var uSelId = uSelValArr[1];
	$input.val(uSelVal);
	_u_sel_auto_val = uSelId;
}

/**
 * 模糊匹配多选效果
 * 
 * @author wul
 * @param uSelValTemp
 *            用户选择的数值
 * @param $input
 *            控件
 */
function autoEventClickCommonMultiple(uSelValTemp, controlId, $input) {
	$input.val('');
	var path = getRootPatch();
	var uSelValArr = uSelValTemp.split(":");
	var uSelVal = uSelValArr[0];
	var uSelId = uSelValArr[1];
	if (_u_sel_auto_val.indexOf(uSelId) == -1) {
		var lbl_bn = $("<label name='" + uSelId + "'>  " + uSelVal
				+ "  </label>");

		var _imgclose = $("<img></img>");
		$(_imgclose).attr({
			"title" : uSelVal,
			"name" : uSelId,
			"src" : "" + path + "/style/images/close.gif"
		});

		$(_imgclose).click(function() {
			removebnlables(this);
		});
		$("#" + controlId).append(lbl_bn);
		$("#" + controlId).append(_imgclose);
		_u_sel_auto_val += uSelId + ",";
	}
}

/**
 * 模糊匹配点击事件
 * 
 * @author wul
 * @param uSelValTemp
 *            用户选择的数值
 * @param controlId
 *            控件ID
 */
function autoEventClickCommon(uSelValTemp, controlId) {
	var path = getRootPatch();
	var uSelValArr = uSelValTemp.split(":");
	var uSelVal = uSelValArr[0];
	var uSelId = uSelValArr[1];
	if (_u_sel_auto_val.indexOf(uSelId) == -1) {
		var lbl_bn = $("<label name='" + uSelId + "'>  " + uSelVal
				+ "  </label>");

		var _imgclose = $("<img></img>");
		$(_imgclose).attr({
			"title" : uSelVal,
			"name" : uSelId,
			"src" : "" + path + "/style/images/close.gif"
		});

		$(_imgclose).click(function() {
			removebnlables(this);
		});
		$("#" + controlId).append(lbl_bn);
		$("#" + controlId).append(_imgclose);
		_u_sel_auto_val += uSelId + ",";
	}
}

function doEditChkbox(control) {
	var controlChk = $(control).is(':checked');
	var controlId = $(control).attr('id');
	var controlAlt = $(control).attr('alt');

	$("[alt=" + controlId + "]").click();
	$("[alt=" + controlId + "]").attr("checked", controlChk);
	if (controlChk) {
		$("#" + controlAlt + "").attr("checked", controlChk);
	} else {
		var controlStatus = false;
		$("[alt=" + controlAlt + "]").each(function(i, val) {
			var controlChkTemp = $(val).is(':checked');
			if (controlChkTemp) {
				controlStatus = true;
			}
		});
		$("#" + controlAlt + "").attr("checked", controlStatus);
	}
}

function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		XMLHttpR = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			XMLHttpR = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpR = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
function sendRequest(url) {
	window.location.href = url;
}
function processResponse() {
	if (XMLHttpR.readyState == 4 && XMLHttpR.status == 200) {
		document.write(XMLHttpR.responseText);
	}
}
var _imageRoot = "/CBA_V2/images/";
var _IEVersion = "";
function cgds(val) {
	var s = val + "";
	if (10 != s.length) {
		if (null != val && "" != val) {
			var year = parseInt(val.year) + 1900;
			var month = (parseInt(val.month) + 1);
			month = month > 9 ? month : ("0" + month);
			var date = parseInt(val.date);
			date = date > 9 ? date : ("0" + date);
			var time = year + "-" + month + "-" + date;
			return time;
		}
	} else {
		return val;
	}
}

/**
 * 重置表单
 * 
 * @author wul
 */
function resetForm(pcId) {
	var iptLst = $("#" + pcId).find("input");
	$.each(iptLst, function(i, val) {
		$(val).val('');
		$(val).attr('alt', '');
	});

	var txtareaLst = $("#" + pcId).find("textarea");
	$.each(txtareaLst, function(i, val) {
		$(val).val('');
	});

	var selLst = $("#" + pcId).find("select");
	var optLst;
	$.each(selLst, function(i, val) {
		optLst = $(val).find('option');
		$.each(optLst, function(i, v) {
			if ($(v).attr('value') == -1) {
				$(v).attr("selected", "selected");
			} else {
				$(v).removeAttr("selected");
			}
		});
	});
	$('#' + pcId).find("select").prop('selectedIndex', 0);
}


 



/**
 * 判断IE版本
 * 
 * @author wul
 */
function chkIENumber() {
	var isIE = !!window.ActiveXObject;
	var isIE6 = isIE && !window.XMLHttpRequest;
	var isIE8 = isIE && !!document.documentMode;
	var isIE7 = isIE && !isIE6 && !isIE8;
	if (isIE) {
		if (isIE6) {
			_IEVersion = "IE6";
		} else {
			if (isIE7) {
				_IEVersion = "IE7";
			} else {
				if (isIE8) {
					_IEVersion = "IE8";
				}
			}
		}
	}
}

/**
 * 格式化CST日期的字串
 * 
 * @author maowei
 */
function formatCSTDate(strDate, format) {
	return formatDate(new Date(strDate), format);
}

/**
 * 格式化日期的字串 补零
 * 
 * @author maowei
 */
function formatCSTDate1(strDate, format) {
	return formatDate(new Date(new Date(strDate).toUTCString()), format);
}


/**
 * 格式化日期
 * 
 * @author maowei
 */
function formatDate(date, format) {

	var paddNum = function(num) {
		num += "";
		return num.replace(/^(\d)$/, "0$1");
	};

	// 指定格式字符
	var cfg = {
		yyyy : date.getFullYear(), // 年 : 4位
		yy : date.getFullYear().toString().substring(2), // 年 : 2位
		M : date.getMonth() + 1, // 月 : 如果1位的时候不补0
		MM : paddNum(date.getMonth() + 1), // 月 : 如果1位的时候补0
		d : date.getDate(), // 日 : 如果1位的时候不补0
		dd : paddNum(date.getDate()), // 日 : 如果1位的时候补0
		hh : paddNum(date.getHours() + 0), // 时
		mm : paddNum(date.getMinutes() + 0), // 分
		ss : paddNum(date.getSeconds() + 0)
	// 秒
	};

	format || (format = "yyyy-MM-dd hh:mm:ss");

	return format.replace(/([a-z])(\1)*/ig, function(m) {
		return cfg[m];
	});
}

function formatDateText(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var M = date.getMinutes();
	var s = date.getSeconds();
	return y + "-" + m + "-" + d + " " + h + ":" + M + ":" + s;
}

/**
 * 构建生成select
 * 
 * @author wul
 * @param obj
 * @param prefixname
 */
function buildselect(selectid, text, value) {
	var id = "#" + selectid;
	$(id)[0].options.add(new Option(text, value));
}
/**
 * 判断一个字符是否存在一个数组里面
 * 
 * @author wul
 * @param arrStrTemp
 *            数组参数
 */
String.prototype.wlIn = function(arrStrTemp) {
	var arr = arrStrTemp.split(",");
	var strTemp = this + "";
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == strTemp) {
			return true;
		}
	}
	return false;
};
/**
 * 去空格 L,R,ALL
 * 
 * @author wul
 * @param obj
 */
String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
};

/**
 * 将数据填充到控件
 * 
 * @author wul
 * @param obj
 *            集合
 * @param prefixname
 *            判断类型
 */
function iterObj(obj, prefixname) {
	var patrnheadertime = /^.+(_time)$/;
	var patrnheader = /^.+(Time)$/;
	var patrnheaderdate = /^.+(_date)$/;
	for ( var key in obj) {
		var compentVal = obj[key];
		if ("object" != typeof (compentVal)) {
			if("string" == typeof(compentVal))
				compentVal=compentVal.replace(new RegExp('00:00:00','gm'),"");
			if ("lbl" == prefixname) {
				$("#" + prefixname + "_" + key + "").html(compentVal);
			}
			if ("lbl2" == prefixname) {
				$("#" + prefixname + "_" + key + "").html(compentVal);
			}
			if ("txt" == prefixname || "ipt" == prefixname) {
			}
			if($("#" + prefixname + "_" + key + "").type="input"){
				$("#" + prefixname + "_" + key + "").val(compentVal);
			}
			$("#" + prefixname + "_" + key + "").html(compentVal);
		}
		if ("object" == typeof (compentVal)) {
			var tt = "";
			if ((patrnheadertime.test(key + "") || patrnheader.test(key + "") || patrnheaderdate
					.test(key + ""))
					&& null != compentVal) {
				compentVal = formatetime(compentVal, 0);
				if ("lbl" == prefixname) {
					$("#" + prefixname + "_" + key + "").html(compentVal);
				}
				if ("lbl2" == prefixname) {
					$("#" + prefixname + "_" + key + "").html(compentVal);
				}
				if ("txt" == prefixname || "ipt" == prefixname) {
					$("#" + prefixname + "_" + key + "").val(compentVal);
				}
			} else {
				for ( var key2 in compentVal) {
					tt = compentVal[key2];
					if ("lbl" == prefixname) {
						$("#" + prefixname + "_" + key + "_" + key2 + "").html(
								tt);
					}
					if ("txt" == prefixname || "ipt" == prefixname) {
						$("#" + prefixname + "_" + key + "_" + key2 + "").val(
								tt);
					}
				}
			}
		}
	}
}

/**
 * 执行aAjax统一方法
 * 
 * @author wul
 * @param ajax_params
 *            ajax参数格式
 */
var ajax_sync = 0;
function ajax_(ajax_params) {
	if (ajax_sync == 0) {
		ajax_sync = 1;
		$.ajax({
			type : ajax_params.type,
			dataType : ajax_params.dataType,
			ifModified : true,
			url : ajax_params.url,
			data : ajax_params.data,
			error : function() {
				if (ajax_sync != 0) {
					ajax_sync = 0;
				}
				ajax_params.error;
			},
			success : function(data) {
				if (ajax_sync != 0) {
					ajax_sync = 0;
				}
				ajax_params.success(data);
			}
		});
	}
}
/**
 * 取得项目名称
 * 
 * @author wul
 */
function getRootName() {
	// 取得当前URL
	var path = window.document.location.href;
	// 取得主机地址后的目录
	var pathName = window.document.location.pathname;
	var post = path.indexOf(pathName);
	// 取得主机地址
	var hostPath = path.substring(0, post);
	// 取得项目名
	var name = pathName.substring(0, pathName.substr(1).indexOf("/") + 1);
	return name;
}
/**
 * 取得项目路径
 * 
 * @author wul
 */
function getRootPatch() {
	// 取得当前URL
	var path = window.document.location.href;
	// 取得主机地址后的目录
	var pathName = window.document.location.pathname;
	var post = path.indexOf(pathName);
	// 取得主机地址
	var hostPath = path.substring(0, post);
	// 取得项目名
	var name = pathName.substring(0, pathName.substr(1).indexOf("/") + 1);
	
	//访问路径中没有项目名称时，name为Modules，有用到getRootPatch()的地方会出现找不到服务的错误，此处将Modules换成项目名称
	//没有项目名称的访问路径http:192.168.1.144:8080，name=/Modules 
	//有项目名称的访问路径http:192.168.1.144:8080/dwjd_hnst，name=/dwjd_hnst
	if("/Modules"==name){
		name = "/dwjd_hnst";
	}
	return hostPath+name;
}
String.prototype.wlInArr = function(arr) {
	var strTemp = this + "";
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == strTemp) {
			return true;
		}
	}
	return false;
};

Array.prototype.remove = function(strTemp) {
	var arr = this;
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == strTemp) {
			arr.splice(i, 1);
		}
	}
}
function formatDateCommon(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + "-" + m + "-" + d;
}
/**
 * 判断字符的长度 返回true str 检验的字符串 count 最大长度
 */
function widthCheck(str, count) {
	var w = 0;
	for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		// 单字节加1
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
			w++;
		} else {
			w += 2;
		}
	}
	if (w > count) {
		return false;
	}
	return true;
}

// 拦截特殊字符
function CheckCode(t) {
	if (/[\':;*?~`!【】￥@#$%^&+={}\[\]\<\>\(\),\.]/.test(t)) {
		return true;// 含有特殊字符!
	}
	return false;// 没含有特殊字符!
}
function openwindow(url, name, iWidth, iHeight) {
	var url; // 转向网页的地址;
	var name; // 网页名称，可为空;
	var iWidth; // 弹出窗口的宽度;
	var iHeight; // 弹出窗口的高度;
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
	window
			.open(
					url,
					name,
					'height='
							+ iHeight
							+ ',,innerHeight='
							+ iHeight
							+ ',width='
							+ iWidth
							+ ',innerWidth='
							+ iWidth
							+ ',top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
}

function validValue(email, phoneNum, Num, enAndNum, cnStr, strLegth, inputValue) {
	// alert(Num);
	var emailRe = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var phoneRe = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
	var NumRe = /^\d+$/;
	var enAndNumRe = /[a-zA-Z0-9]{6,16}/;
	var cnStrRe = /^[u4E00-u9FA5]+$/;
	var strLegthRe = /^[\w\W]{6,16}$/;
	switch (inputValue) {
	case email:
		if (email == "") {
			$.messager.alert("提示信息", "邮箱地址不能为空！", "error");
		} else if (!emailRe.test(email) && email != "") {
			$.messager.alert("提示信息", "请输入正确邮箱！", "error");
			return;
		}
		break;

	case phoneNum:
		if (phoneNum == "") {
			$.messager.alert("提示信息", "手机号码不能为空！", "error");
		} else if (!phoneRe.test(phoneNum) && phoneNum != "") {
			// alert(re.test($('#phoneNum').val());
			$.messager.alert("提示信息", "请输入正确的手机号码！", "error");
			return;
		}
		break;

	case Num:

		if (Num == "") {
			$.messager.alert("提示信息", "输入不能为空！", "error");
		} else if (!NumRe.test(Num) && Num != "") {
			$.messager.alert("提示信息", "只能英文和数字！", "error");
			return;
		}
		break;

	case enAndNum:

		if (enAndNum == "") {
			$.messager.alert("提示信息", "文字输入不能为空！", "error");
		} else if (!enAndNumRe.test(enAndNum) && enAndNum != "") {
			$.messager.alert("提示信息", "只能数字！", "error");
			return;
		}
		break;

	case cnStr:

		if (cnStr == "") {
			$.messager.alert("提示信息", "文字输入不能为空！", "error");
		} else if (!cnStrRe.test(cnStr) && cnStr != "") {
			$.messager.alert("提示信息", "只能数字！", "error");
			return;
		}
		break;

	case strLegth:

		if (strLegth == "") {
			$.messager.alert("提示信息", "文字输入不能为空！", "error");
		} else if (!strLegthRe.test(strLegth) && strLegth != "") {
			$.messager.alert("提示信息", "只能数字！", "error");
			return;
		}
		break;
	}
}




/**
 * 居中弹出窗口
 * 
 * @author Maowei
 * @param url
 * @param name
 * @param iWidth
 * @param iHeight
 * @param iscrollbars
 * @param iresizable
 */
function openWindowInC(url, name, iWidth, iHeight, iscrollbars, iresizable) {
    var url; //转向网页的地址;
    var name; //网页名称，可为空;
    var iWidth; //弹出窗口的宽度;
    var iHeight; //弹出窗口的高度;
    var iscrollbars; //弹出窗口的是否有滚动条(auto/yes/no);
    var iresizable; //弹出窗口是否可调整大小(yes/no);
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
    window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=' + iscrollbars + ',resizable=' + iresizable + ',location=no,status=no');
}

/**
 * 居中弹出窗口
 * 
 * @author Maowei
 * @param url
 * @param name
 * @param iWidth
 * @param iHeight
 * @param iscrollbars
 * @param iresizable
 */
function openWindowInC(url, name, iWidth, iHeight, iscrollbars, iresizable) {
    var url; //转向网页的地址;
    var name; //网页名称，可为空;
    var iWidth; //弹出窗口的宽度;
    var iHeight; //弹出窗口的高度;
    var iscrollbars; //弹出窗口的是否有滚动条(auto/yes/no);
    var iresizable; //弹出窗口是否可调整大小(yes/no);
    var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
    window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=' + iscrollbars + ',resizable=' + iresizable + ',location=no,status=no');
}

/**
 * 居中弹出窗口（从父页面）
 * 
 * @author Maowei
 * @param url
 * @param name
 * @param iWidth
 * @param iHeight
 * @param iscrollbars
 * @param iresizable
 */
function openWindowFromFather(url, name, iWidth, iHeight, iscrollbars, iresizable) {
	var url; // 转向网页的地址;
	var name; // 网页名称，可为空;
	var iWidth; // 弹出窗口的宽度;
	var iHeight; // 弹出窗口的高度;
	var iscrollbars; // 弹出窗口的是否有滚动条(auto/yes/no);
	var iresizable; // 弹出窗口是否可调整大小(yes/no);
	var iTop = (window.opener.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.opener.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
	window.opener.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight
			+ ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop
			+ ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars='
			+ iscrollbars + ',resizable=' + iresizable
			+ ',location=no,status=no');
}

function jsonToString (obj){   
		
        var THIS = this;      
        switch(typeof(obj)){  
            case 'string':     
                return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';     
            case 'array':     
                return '[' + obj.map(THIS.jsonToString).join(',') + ']'; 
			case 'boolean':
				return obj;
            case 'object':   
                 if(obj instanceof Array){
                    var strArr = []; 
                    var len = obj.length;
                    for(var i=0; i<len; i++){
                        strArr.push(THIS.jsonToString(obj[i]));
                    }     
                    return '[' + strArr.join(',') + ']';
                }else if(obj==null){
                    return 'null';     
    
                }else{ 
                    var string = [];     
                    for (var property in obj) {
						
						string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));     
					}
                    return '{' + string.join(',') + '}';     
                }     
            case 'number':     
                return obj;
            case false:     
                return obj;
        }     
   }  

function jsonToStringToLowerCase(obj){     
        var THIS = this;      
        switch(typeof(obj)){  
            case 'string':     
                return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';     
            case 'array':     
                return '[' + obj.map(THIS.jsonToString).join(',') + ']';     
            case 'object':   
                 if(obj instanceof Array){
                    var strArr = []; 
                    var len = obj.length;
                    for(var i=0; i<len; i++){
                        strArr.push(THIS.jsonToString(obj[i]));
                    }     
                    return '[' + strArr.join(',') + ']';
                }else if(obj==null){
                    return 'null';     
    
                }else{ 
                    var string = [];     
                    for (var property in obj) string.push(THIS.jsonToString(property).toLowerCase() + ':' + THIS.jsonToString(obj[property]));     
                    return '{' + string.join(',') + '}';     
                }     
            case 'number':     
                return obj;     
            case false:     
                return obj;     
        }     
   } 

function formToJsonOld(form){
	var str = $('#'+form).serialize();
	str = str.replace(/\+/g," ");
	str = decodeURIComponent(str);
	str = str.replace(/&/g,"','");
	str = str.replace(/=/g,"':'");
	str = "({'"+str +"'})";
	str = str.replace(/\r/g, '\\r').replace(/\n/g, '\\n'); 
    obj = eval(str); 
	return obj;
}

function formTojson(str)
{
	var strs="#"+str;
    var obj=new Object();  
    /*$.each($(strs).serializeArray(), function() {  
    	var namelow = 	this.name.toLowerCase();
        if (obj[namelow]) {  
            if (!obj[namelow].push) {  
            	obj[namelow] = [obj[namelow]];  
            }  
            obj[namelow].push(this.value || '');  
        } else {  
        	obj[namelow] = this.value || '';  
        }  
    });*/
   //alert(jsonToString(obj));
    $.each($(strs).serializeArray(),function(index,param){
    	var paramlow = 	param.name.toLowerCase();
        if(!(paramlow in obj)){
	        //日期 正则
	        var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
	        if(param.value.match(reg)){//是 yyyy-MM-dd格式的 则变成 yyyy-MM-dd 00:00:00
	        	param.value = param.value+" 00:00:00";
	        }
	        
	        obj[paramlow]=param.value;  
        }else{
        	 obj[paramlow]= obj[paramlow] + "," +param.value;  
        }
    });  
    return obj;  
};

function convertFormToJson(str){
    var obj=new Object();  
    $.each($("#"+str).serializeArray(),function(index,param){
        if(!(param.name in obj)){
	        obj[param.name]=param.value;
        }
    });  
    return obj;  
};

function strToObj(json){
    return eval("("+json+")"); 
}

/**
 * 关闭窗口
 */
function closeWindow(windowId){
	$('#'+windowId).window('close');
}

/**
 * 清空表单
 */
function resetForm(formId){
	$("#"+formId).form('clear');
}

/**
 * 提示信息
 */
function alertMsg(title,messager,type){
	$.messager.alert(title, messager,type);
}
/**
 * 字符串转JSON
*/
function strToJson(str){ 
	var json = (new Function("return " + str))(); 
	return json; 
}

//填充form的input值，在页面初始化完成以后
function fillFormAfter(formId,jsonData) {
	//取得所有的easyui-combobox控件
	$("#" + formId + " .fb-combobox").each(function () {
		if($(this).attr('childId')!='' && $(this).attr('childId')!=undefined){
			var name = $(this).attr('comboname');
			var valObj = jsonData[name];
			if(typeof(valObj) == 'object'){
				 $(this).combobox('setValues',valObj);
			}else{
				if(valObj!=''){
					 $(this).combobox('setValue',jsonData[name]);
				}
			}
			delete jsonData[name];
		}
	});
	$("#" + formId).form('load',jsonData);
}

//填充form的input值，页面初始化期间
function fillFormBefore(formId,jsonData) {
	//取得所有的easyui-combobox控件
	$("#" + formId + " .fb-combobox").each(function () {
		if($(this).attr('childId')!='' && $(this).attr('childId')!=undefined){
			var name = $(this).attr('name');
			var valObj = jsonData[name];
			if(typeof(valObj) == 'object'){
				//$(this).combobox('setValues',valObj);
			}else{
				if(valObj!=''){
					$(this)[0].value = valObj;
				}
			}
			delete jsonData[name];
		}
	});
	$("#" + formId).form('load',jsonData);
}
//禁用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]
function disableForm(formId,isDisabled) {
  var attr="disable";
	if(!isDisabled){
	   attr="enable";
	}
	$("form[id='"+formId+"'] :text").attr("disabled",isDisabled);
	$("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);
	$("form[id='"+formId+"'] select").attr("disabled",isDisabled);
	$("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);
	$("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);
	//禁用jquery easyui中的下拉选（使用input生成的combox）
	$("#" + formId + " .easyui-combobox").each(function () {
		$(this).combobox(attr);
	});
	
	//禁用jquery easyui中的日期组件dataBox
	$("#" + formId + " .datebox").each(function () {
		$(this).datebox(attr);
	});
}

/**
 * 禁用退格键在readonly或者disable属性的input控件上
*/
function disableBackSpace(){
	$("input[readonly]").keydown(function(e) {
		if(e.keyCode==8){
			 e.preventDefault();
		}
	});
}

/*
 *求2个日期相差的天数 
 * */
function  DateDiff(DateOne,  DateTwo){    //sDate1和sDate2是2006-12-18格式    
    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
    return Math.abs(cha); 
}

/*
 * 获取当前日期 格式yyyy-mm-dd
 * */

function getNowDate(){
	var now=new Date()
	y=now.getFullYear()
	m=now.getMonth()+1
	d=now.getDate()
	m=m<10?"0"+m:m
	d=d<10?"0"+d:d
	return  y+"-"+m+"-"+d ;
}




/*
 * 2个日期的大小比较
 * */
function duibi(a, b) {
	 var date1 = new Date(a.replace(/\-/g, "\/"));
	 var date2 = new Date(b.replace(/\-/g, "\/"));
	 var res = date1 - date2;
	 if(res>=0){
		 return true;
	 }else
		 return false;

}

function loadDatagrid(iframeId,datagridId){
	parent.window.frames[iframeId].contentWindow.$('#'+datagridId).datagrid('load');
}

function loadDatagridForJTCY(iframeId){
	parent.window.frames[iframeId].contentWindow.refreshGrList();
}

function convertComplexObjectToSimpleObject(data){
	for(var key in data){
		var obj=data[key];
		switch(typeof(obj)){  
			case 'object':   
			if(obj instanceof Array){
				for(var i=0;i<obj.length;i++){
					if(obj[i] instanceof Object){
						for(var objKey in obj[i]){
							data[key+"["+i+"]."+objKey]=obj[i][objKey];
						}
					}else{
							data[key+"["+i+"]"]=obj[i];
					}
				}
				delete data[key];
				}else if(obj!=null){
					for(var objKey in obj){	  
						data[key+"."+objKey]=obj[objKey];
					}
					delete data[key];
				}     
		}     
	}
	return data;
}

function rtrim_dwdm(dwdm){
	while(dwdm.substring(dwdm.length-1,dwdm.length)=="0"){
		dwdm=dwdm.substring(0,dwdm.length-1);
	}
	return dwdm;
}
$(document).ready(disableBackSpace);

function copydy(copyText) { 
	var props = "";  
	  for(var p in copyText){  
	      if(typeof(copyText[p])=="function"){  
	    	  copyText[p]();  
	      }else{  
	            props+= p + "=" + copyText[p] + "\n";  
	         }  
	  }  
if (window.clipboardData) { 
window.clipboardData.setData("Text", props) ;
} 
else { 
var flashcopier = 'flashcopier'; 
if(!document.getElementById(flashcopier)) { 
var divholder = document.createElement('div'); 
divholder.id = flashcopier; 
document.body.appendChild(divholder); 
} 
document.getElementById(flashcopier).innerHTML = ''; 
var divinfo = '<embed src="../js/_clipboard.swf" FlashVars="clipboard='+encodeURIComponent(props)+'" width="0" height="0" type="application/x-shockwave-flash"></embed>'; 
document.getElementById(flashcopier).innerHTML = divinfo; 
} 
alert('copy成功\n'+props); 
} 

/**
 * 解决Easyui Datagrid行号四位、五位显示不完全的扩展方法
 * 在onLoadSuccess调用,例如
 * $("#grid").datagrid({
 *   ...
 *   onLoadSuccess: function() {
 *     $(this).datagrid("fixRownumber");
 *   }
 * });
 */
$.extend($.fn.datagrid.methods, {
  fixRownumber: function(jq) {
    return jq.each(function() {
      var panel = $(this).datagrid("getPanel");
      var clone = $(".datagrid-cell-rownumber", panel).last().clone();
      clone.css({
        "position": "absolute",
        left: -1000
      }).appendTo("body");
      
      var width = clone.width("auto").width();
      //默认宽度是25,所以只有大于25的时候才进行fix
      if(width > 25) {
        //多加几个像素,保持一点边距
        $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 10);
        
        //修改了宽度之后,需要对容器进行重新计算
        $(this).datagrid("resize");
        
        clone.remove();
        clone = null;
      } else {
        //还原成默认状态
        $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).removeAttr("style");
      }
    });
  }
});
