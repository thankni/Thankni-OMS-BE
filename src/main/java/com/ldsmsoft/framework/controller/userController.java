package com.ldsmsoft.framework.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldsmsoft.framework.dao.mybatis.model.UserBean;
import com.ldsmsoft.framework.service.MasService;
import com.ldsmsoft.framework.service.UserService;
import com.ldsmsoft.framework.util.DateUtil;
import com.ldsmsoft.framework.util.Util;

@Controller
@RequestMapping(value="/user")
public class userController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MasService masService;

	/**
	 * 主页呈现页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index.do")
	public String main(HttpServletRequest request){
		return "/user/index";
	}
	
	/**
	 * 注册呈现页
	 * @return 呈现页url
	 */
	@RequestMapping(value="/regIndex.do",method=RequestMethod.GET)
	public String regIndex(){
		return "/user/reg";
	}
	
	/**
	 * 注册提交
	 * @param request
	 * @param model
	 */
	@ResponseBody
	@RequestMapping(value="/regSubmit.do")
	public String regSubmit(HttpServletRequest request,Model model){
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		String identifyCode = request.getParameter("identifyCode");
		String userString = request.getParameter("user");
		ObjectMapper ob = new ObjectMapper();
		try {
			//转换json字符串为bean对象
			UserBean bean = ob.readValue(userString, UserBean.class);
			//对用户填写的验证码进行校验
			boolean rs = masService.validating(bean.getTel(), identifyCode);
			
			if(rs){
				//注册
				resultMap = userService.regUser(bean.getName(),bean.getIdcard(),bean.getIcno(),bean.getTel(),bean.getPassword());
			}else{
				resultMap.put("message", "短信验证码不匹配，请核实！");
				resultMap.put("resultType","error");
			}
			
		} catch (Exception e) {
			resultMap.put("message", "发生异常，异常信息为："+e.getCause().getMessage());
			resultMap.put("resultType","error");
			return JSON.toJSON(resultMap).toString();
		}
		return JSON.toJSON(resultMap).toString();
	}
	/**
	 * 注册成功呈现页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/regSuccess.do")
	public String regSuccess(HttpSession session){
		return "/user/regSuccess";
	}
	/**
	 * 登录呈现页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login.do")
	public String logIndex(HttpServletRequest request){
		return "/user/login";
	}
	
	/**
	 * 登录提交
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loginSubmit.do")
	public String loginSubmit(HttpServletRequest request,HttpSession session,UserBean bean,Model model){
		String returnStr = "";
		UserBean user = userService.selectByUserBean(bean);
		if(user!=null){
			session.setAttribute("currentUser", user);
			returnStr= "redirect:/user/userCenter.do";
		}else{
			model.addAttribute("message", "用户名或密码错误！");
			model.addAttribute("type", "error");
			returnStr = "/user/login";
		}
		return returnStr;
	}
	/**
	 * 跳转个人中心页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/userCenter.do")
	public String userCenter(HttpServletRequest request,HttpSession session,UserBean bean,Model model){
		String returnStr = "";
		UserBean user = (UserBean) session.getAttribute("currentUser");
		if(user!=null){
			
			//身份证号码加密处理，除了前面两位和后面两位，其他都替换成*
			String idcard = user.getIdcard();
			idcard = Util.idcardEncrypt(idcard);
			
			//社保卡号加密处理，除了首位和末位，其他都替换成*
			String icno = user.getIcno();
			icno = Util.icnoEncrypt(icno);
			
			//手机号加密处理，除了前面三位和后面两位，其他都替换成*
			String tel = user.getTel();
			tel = Util.telEncrypt(tel);
			
			model.addAttribute("name",user.getName());
			model.addAttribute("idcard",idcard);
			model.addAttribute("icno",icno);
			model.addAttribute("tel",tel);
			
			returnStr = "/user/userCenter";
		}else{
			model.addAttribute("message", "用户会话失效，请重新登录！");
			model.addAttribute("type", "error");
			returnStr = "/user/login";
		}
		return returnStr;
	}
	/**
	 * 修改密码呈现页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editPwd.do")
	public String eidtPwd(HttpServletRequest request){
		return "/user/editPwd";
	}
	
	/**
	 * 验证手机号与当前用户的手机号是否相符,如果不符合，返回false，否则返回true(返回json数据，格式为{"valid",true})
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/validateTel.do",produces="application/json;charset=UTF-8")
	public String validateTel(HttpServletRequest request,HttpSession session,@RequestParam String tel){
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		Boolean result = true;
		UserBean user = (UserBean) session.getAttribute("currentUser");
		if(user!=null){
			//判断输入手机号是否与该用户的绑定手机号相同
			if(tel.equals(user.getTel())){
				result = true;
			}else{
				result = false;
			}
		}
		map.put("valid", result);
		ObjectMapper mapper = new ObjectMapper();
		String resultString = "";
		try {
			resultString = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resultString;
	}	
	/**
	 * 修改密码
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/editReg.do")
	public String editReg(HttpServletRequest request,HttpSession session ,Model model){
		UserBean user = (UserBean) session.getAttribute("currentUser");
		String pwd = request.getParameter("password");
		user.setPassword(pwd);
		//user.setUpdatetime(DateUtil.getCurrentDateTime());
		userService.eidtRegInfo(user);
		
		//修改密码成功后，需要注销当前登录信息，重新登录
		session.removeAttribute("currentUser");
		
		return "redirect:/user/editSuccess.do";
	}
	/**
	 * 修改密码成功呈现页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editSuccess.do")
	public String editSuccess(HttpSession session){
		//手动杀死会话
		session.invalidate();
		return "/user/editSuccess";
	}	
	/**
	 * 退出登录
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session){
		//手动杀死会话
		session.invalidate();
		return "/user/login";
	}
	
	/**
	 * session失效后的跳转连接
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loginError.do")
	public String loginError(Model model){
		model.addAttribute("message", "用户会话失效，请重新登录！");
		model.addAttribute("type", "error");
		return "/user/login";
	}
	
	/**
	 * 修改手机呈现页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editTel.do")
	public String editTel(HttpServletRequest request){
		return "/user/editTel";
	}
	
	/**
	 * 发送手机验证码
	 * @param tel 目标手机号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendSMS.do")
	public String sendSMS(@RequestParam String tel){
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		
		boolean result = true;
		result = masService.sendSMS(tel);
		map.put("result", result);
		
		//map转json
		ObjectMapper mapper = new ObjectMapper();
		String resultString = "";
		try {
			resultString = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resultString;
	}	
	
	/**
	 * 验证用户填写的验证码
	 * @param tel 目标手机号
	 * @param idenCode 验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/validIdenCode.do")
	public String validIdenCode(@RequestParam String tel,@RequestParam String idenCode){
		Map<String,Object> map = new HashMap<String,Object>();
		
		boolean result = true;
		//根据手机号及验证码进行验证
		result = masService.validating(tel, idenCode);
		map.put("valid",result);
		
		ObjectMapper mapper = new ObjectMapper();
		String resultString = "";
		try {
			resultString = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * 修改手机号
	 * @param request
	 * @param session
	 * @param model
	 * @param tel 新手机号
	 * @param indenCode 短信验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveTel.do")
	public String saveTel(HttpSession session,@RequestParam String tel,@RequestParam String idenCode){
		Map<String,Object> map = new HashMap<String,Object>();
		
		//根据手机号及验证码进行验证
		boolean rs = masService.validating(tel,idenCode);
		if(rs){
			UserBean user = (UserBean) session.getAttribute("currentUser");
			user.setTel(tel);
			//user.setUpdatetime(DateUtil.getCurrentDateTime());
			userService.eidtRegInfo(user);
			
			//更新session中的用户信息
			session.setAttribute("currentUser",user);
			
			//修改密码成功后，需要注销当前登录信息，重新登录
			//session.removeAttribute("currentUser");
			map.put("result",true);
		}else{
			map.put("result",false);
		}
		
		//map转json
		ObjectMapper mapper = new ObjectMapper();
		String resultString = "";
		try {
			resultString = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * 手机号修改成功,跳转回用户信息页
	 * @return
	 */
	@RequestMapping(value="/saveTelSuccess.do")
	public String saveTelSuccess(HttpSession session,Model model){
		UserBean user = (UserBean) session.getAttribute("currentUser");
		
		//身份证号码加密处理，除了前面两位和后面两位，其他都替换成*
		String idcard = user.getIdcard();
		idcard = Util.idcardEncrypt(idcard);
		
		//社保卡号加密处理，除了首位和末位，其他都替换成*
		String icno = user.getIcno();
		icno = Util.icnoEncrypt(icno);
		
		//手机号加密处理，除了前面三位和后面两位，其他都替换成*
		String tel = user.getTel();
		tel = Util.telEncrypt(tel);
		
		model.addAttribute("name",user.getName());
		model.addAttribute("idcard",idcard);
		model.addAttribute("icno",icno);
		model.addAttribute("tel",tel);
		return "/user/userCenter";
	}	
}
