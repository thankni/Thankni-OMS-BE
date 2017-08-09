package com.common.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.common.hibernate.entity.YbzhkdLog;
import com.insigma.odin.framework.persistence.HBUtil;

public class JkLogUtil {

	/**
	 * 增加日志
	 * 
	 * @param dto
	 * @param sess
	 * @return
	 * @throws Exception
	 */
	public static Integer addLog(YbzhkdLog log) throws Exception {
		Connection conn = HBUtil.getHBSession().connection();

		int zhsqId = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("select SEQ_QMCBJK_LOG.nextval from dual");
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				zhsqId = rst.getInt(1);
			}
			if (zhsqId == 0) {
				throw new Exception("获取操作日志序列失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("新增加日志失败：" + e.getMessage(), e);
		} finally {
		}

		return zhsqId;
	}

	/**
	 * 又返回之后更新日志
	 * 
	 * @param log
	 * @return
	 * @throws Exception
	 */
	public static YbzhkdLog updateLog(YbzhkdLog log) throws Exception {
		Connection conn = HBUtil.getHBSession().connection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into qmcbjk_log(args_in,ri_ret,ri_code,rv_msg,czrq,lsh,zhsq_name,result_out,clientid,clientlsh,log_id) values (?,?,?,?,sysdate,?,?,?,?,?,?)");
			pstmt.setString(1, log.getArgsIn() != null
					? log.getArgsIn().length() > 1000 ? log.getArgsIn().substring(0, 1000) : log.getArgsIn() : "");
			pstmt.setString(2, log.getRiRet());
			pstmt.setString(3, log.getRiCode());
			pstmt.setString(4, log.getRvMsg() != null
					? log.getRvMsg().length() > 1000 ? log.getRvMsg().substring(0, 1000) : log.getRvMsg() : "");
			pstmt.setString(5, log.getLsh());
			pstmt.setString(6, log.getZhsqName());
			pstmt.setString(7, log.getResultOut() != null
					? log.getResultOut().length() > 1000 ? log.getResultOut().substring(0, 1000) : log.getResultOut()
					: "");

			pstmt.setString(8, log.getClientId());
			pstmt.setString(9, log.getClientLsh() != null
					? log.getClientLsh().length() > 32 ? log.getClientLsh().substring(0, 32) : log.getClientLsh() : "");
			pstmt.setInt(10, log.getLogId());
			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("更新操作日志出错：" + e.getMessage(), e);
		} finally {
		}
		return log;
	}

	/**
	 * 更新错误日志
	 * 
	 * @param log
	 * @return
	 * @throws Exception
	 */
	public static YbzhkdLog updateErrorLog(YbzhkdLog log) throws Exception {
		Connection conn = HBUtil.getHBSession().connection();
		;
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"insert into qmcbjk_log(args_in,ri_code,rv_msg,czrq,clientid,clientlsh,log_id) values (?,?,?,sysdate,?,?,?)");
			pstmt.setString(1, log.getArgsIn() != null
					? log.getArgsIn().length() > 1000 ? log.getArgsIn().substring(0, 1000) : log.getArgsIn() : "");
			pstmt.setString(2, log.getRiCode());
			pstmt.setString(3, log.getRvMsg() != null
					? log.getRvMsg().length() > 1000 ? log.getRvMsg().substring(0, 1000) : log.getRvMsg() : "");
			pstmt.setString(4, log.getClientId());
			pstmt.setString(5, log.getClientLsh() != null
					? log.getClientLsh().length() > 32 ? log.getClientLsh().substring(0, 32) : log.getClientLsh() : "");
			pstmt.setInt(6, log.getLogId());
			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("更新操作日志出错：" + e.getMessage(), e);
		} finally {
		}
		return log;
	}
	/**
	 * 增加日志
	 * 
	 * @param dto
	 * @param sess
	 * @return
	 */
	/*
	 * public static String addLog(JkLogDTO dto) { Session
	 * hbsess=HsFactory.currentSession(); Connection conn = hbsess.connection();
	 * CallableStatement proc = null;
	 * 
	 * String id = ""; try { proc = conn.prepareCall(
	 * "{ call pkg_interface_log.ADD_LOG(?,?,?,?,?,?,?) }"); proc.setString(1,
	 * dto.getJkType());// 接口类型 proc.setString(2, dto.getJkOp());// 接口调用方
	 * proc.setString(3, ""); proc.setString(4, ""); proc.setTimestamp(5, new
	 * Timestamp(dto.getAae036().getTime()));// 接口调用时间 //如果长度超过6000,只截取前6000
	 * if(dto.getJkSendInfo().length()>6000){ proc.setString(6,
	 * "请求的字符串长度超过6000"+dto.getJkSendInfo().substring(0,6000)); }else{
	 * proc.setString(6, dto.getJkSendInfo()); } proc.setString(7, id);
	 * proc.registerOutParameter(7, Types.VARCHAR); proc.execute(); id =
	 * proc.getString(7); conn.commit(); if(proc!=null){ proc.close(); } } catch
	 * (Exception e) { e.printStackTrace(); try { conn.rollback(); } catch
	 * (Exception ex) { ex.printStackTrace(); } } return id; }
	 *//**
		 * 修改日志
		 * 
		 * @param dto
		 * @param sess
		 * @return
		 */
	/*
	 * public static void updateLog(JkLogDTO dto) { Session
	 * hbsess=HsFactory.currentSession(); Connection conn = hbsess.connection();
	 * CallableStatement proc = null; try { proc = conn.prepareCall(
	 * "{ call pkg_interface_log.UPDATE_LOG(?,?,?,?,?) }"); proc.setString(1,
	 * dto.getJkId()); //proc.setString(2, dto.getAae100()); proc.setString(2,
	 * dto.getJkResult()); if(dto.getJkMsg().length()>6000){ proc.setString(3,
	 * "返回的字符串长度超过6000"+dto.getJkMsg().substring(0,6000)); }else{
	 * proc.setString(3, dto.getJkMsg()); } proc.setString(4, "");
	 * proc.setString(5, ""); proc.execute(); conn.commit(); if(proc!=null){
	 * proc.close(); } } catch (Exception e) { try { conn.rollback(); } catch
	 * (Exception ex) { ex.printStackTrace(); } e.printStackTrace(); } }
	 *//**
		 * 新增加主日志
		 * 
		 * @param jktype
		 *            接口类型
		 * @param jkop
		 *            接口调用方
		 * @param sendInfo
		 *            接口请求相关信息
		 * @return
		 */
	/*
	 * public static String addLog(String jktype,String jkop,String sendInfo)
	 * throws AppException{ JkLogDTO mainlogdto=new JkLogDTO();
	 * mainlogdto.setAae036(new Date());// 请求操作时间
	 * mainlogdto.setJkSendInfo(sendInfo); mainlogdto.setJkType(jktype);// 接口类型
	 * mainlogdto.setJkOp(jkop); // 调用方:局平台 String jkid = addLog(mainlogdto);
	 * return jkid;
	 * 
	 * }
	 *//**
		 * 更新主日志
		 * 
		 * @param jkId
		 *            主日志流水号
		 * @param jkresult
		 *            接口调用结果
		 * @param jkMsg
		 *            信息
		 * @param sendTotal
		 *            发送总记录数
		 * @param jkTotal
		 *            接收总记录数
		 */
	/*
	 * public static void updateLog(String jkId,String aae011,String
	 * jkresult,String jkMsg){ JkLogDTO mainlogdto=new JkLogDTO();
	 * mainlogdto.setJkId(jkId); mainlogdto.setAae100(aae011);// 请求人
	 * mainlogdto.setJkResult(jkresult);// 失败 mainlogdto.setJkMsg(jkMsg);
	 * updateLog(mainlogdto); }
	 */

}
