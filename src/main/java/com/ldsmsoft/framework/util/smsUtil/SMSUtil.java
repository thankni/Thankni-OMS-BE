package com.ldsmsoft.framework.util.smsUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.webconfig.AppException;
import com.ldsmsoft.framework.dao.mybatis.dao.SMSLogBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.SMSLogBean;
import com.ldsmsoft.framework.util.DateUtil;

public class SMSUtil {

	@Autowired
	private static SMSLogBeanMapper smsLogMapper;
	
	/**
	 * 保存日志发送日志信息
	 * @param smslogId  日志ID
	 * @param phoneNum  电话号码
	 * @param dateTime  发送时间
	 * @param message   消息内容
	 * @return
	 */
	public static boolean saveLog(Long smslogId, String phoneNum,Date dateTime,String message,String idenCode){
		boolean save = true;

		String[] phone =phoneNum.split(",");
		
		for(int i=0;i<phone.length;i++){
			Long id = null;
			if(smslogId==null){
				//根据当前时间生产日志id
				//id=DateUtil.getCurrentDateTime().getTime();
			}else{
				id = smslogId;
			}
			
			SMSLogBean bean = new SMSLogBean();
			bean.setSmslogid(id);
			//bean.setDatetime(DateUtil.getCurrentDateTime());
			bean.setPhonenum(phone[i]);
			bean.setLog(message);
			bean.setIdencode(idenCode);
			
			//数据入库
			smsLogMapper.insertSelective(bean);
		}
		return save;
	}
	
	/**
	 * 用户验证码信息验证类，若验证成功则返回true，否则返回false。验证信息有效时间为5分钟，
	 * 若用户在五分钟后才输入验证码，则校验不通过，需要重新发送验证信息。
	 * @param phoneNum 目标手机号
	 * @param identifyCode  用户输入的验证码
	 * @return
	 */
	public static boolean validating(String phoneNum,String identifyCode){
		boolean validating = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -5);
		SMSLogBean bean = new SMSLogBean();
		
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
		Date dateTime;
		try {
			dateTime = DateUtil.compactStringToDate(time);
			bean.setDatetime(dateTime);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		bean.setPhonenum(phoneNum);
		List<String> list = smsLogMapper.validateSMS(bean);
		if(list.size()>0)
			if(((String)list.get(0)).equals(identifyCode))
				validating = true;
		return validating;
	}
	
}
