package com.ldsmsoft.framework.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.webconfig.AppException;
import com.ldsmsoft.framework.dao.mybatis.dao.SMSLogBeanMapper;
import com.ldsmsoft.framework.dao.mybatis.model.SMSLogBean;
import com.ldsmsoft.framework.util.DateUtil;
import com.ldsmsoft.framework.util.FileReadUtil;
import com.mascloud.sdkclient.Client;

@Service("masService")
public class MasServiceImpl implements MasService {
	
	@Autowired
	private SMSLogBeanMapper smsLogMapper;
	
	private String url; //身份认证地址，请向中国移动集团获得短信发送平台数据URL信息
	private String userAccount; //用户登录账号
	private String password; //用户密码
	private String ecname; //企业名称
	private String sign; //网关签名编码，必填，签名编码在中国移动集团开通帐号后分配，可以在云MAS网页端管理子系统-SMS接口管理功能中下载。
	private String idenExplain; //短信内容模板（不包含验证码）
	
	//读取配置属性
	public void readProperties(){
		url = FileReadUtil.ReadProperties("card.properties","masUrl");
		userAccount = FileReadUtil.ReadProperties("card.properties","masUserAccount");
		password = FileReadUtil.ReadProperties("card.properties","masPassword");
		ecname = FileReadUtil.ReadProperties("card.properties","masEcname");
		sign = FileReadUtil.ReadProperties("card.properties","masSign");
		idenExplain = FileReadUtil.ReadProperties("card.properties","masIdenExplain");
	}
	
	/**
	 * 发送短信验证码，成功后写入数据库
	 * @param tel
	 * @return
	 */
	public boolean sendSMS(String mobile){
		
		boolean rs = false;
		//读取配置属性
		readProperties();
		
		//生成验证码信息,验证码为六位数字
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++){
			int random = (int) (Math.random()*9);	
			sb.append(random);
		}
		String msg=sb.toString()+idenExplain;
				
		final Client client =  Client.getInstance();
		// 测试环境IP，登录验证URL，用户名，密码，集团客户名称
		client.login(url,userAccount,password,ecname);

		int sendResult = client.sendDSMS (new String[] {mobile},
				msg, "", 1, sign, UUID.randomUUID().toString(),true);
		System.out.println("推送结果: " + sendResult);
		System.out.println("消息: " + msg);
		//判断是否发送成功,若发送成功，保存发送日志信息，并保存验证码
		if (sendResult==1) {
			Boolean res = this.saveLog(null,mobile,new Date(),msg,sb.toString());
			if (res){
				rs = true;
			}
		}
		return rs;
	}
	
	/**
	 * 保存日志发送日志信息
	 * @param smslogId  日志ID
	 * @param phoneNum  电话号码
	 * @param dateTime  发送时间
	 * @param message   消息内容
	 * @return
	 */
	public boolean saveLog(Long smslogId, String phoneNum,Date dateTime,String message,String idenCode){
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
	public boolean validating(String phoneNum,String identifyCode){
		boolean validating = false;
		SMSLogBean bean = new SMSLogBean();
		
		//时间的处理，获取当前时间，然后减去5分钟
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -5);
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
		Date dateTime;
		try {
			dateTime = DateUtil.compactStringToDate(time);
			bean.setDatetime(dateTime);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		bean.setPhonenum(phoneNum);
		bean.setIdencode(identifyCode);
		List<String> list = smsLogMapper.validateSMS(bean);
		if(list.size()>0)
			validating = true;
		return validating;
	}
		
/*	public static void main(String[] args) {
		try {
			final Client client =  Client.getInstance();
			// 正式环境IP，登录验证URL，用户名，密码，集团客户名称
			client.login("http://mas.ecloud.10086.cn/app/sdk/login", "zmdrs", "zmdrs","驻马店市人力资源与社会保障局");
			// 测试环境IP
			//client.login("http://112.33.1.13/app/sdk/login", "sdk2", "123","光谷信息");
			int sendResult = client. sendDSMS (new String[] {"13525508786"},
					"sdk短信发送内容测试", "",  1,"quh31sey", UUID.randomUUID().toString(),true);
			System.out.println("推送结果: " + sendResult);
			
			
			//添加黑白名单
			client.addMember("18602761993", "9003451262");
			//查询黑白名单
			client.queryMember("9003451262");
			//删除黑白名单
			client.deleteMember("18602761993", "9003451262");
			
			// 获取提交报告线程
			Thread t1 = new Thread() {
				public void run() {
					while(true) {
						List< SubmitReportModel > list  = client.getSubmitReport();
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t1.start();

			// 获取状态报告线程
			Thread t2 = new Thread() {
				public void run() {
					while(true) {
						List< StatusReportModel > StatusReportlist = client.getReport();
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t2.start();

			// 获取上行线程
			Thread t3 = new Thread() {
				public void run() {
					while(true) {
						List< MoModel> lis = client.getMO();
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t3.start();
			
			for(;;);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
}
