package com.ldsmsoft.framework.util;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

public class Util {
	
	public static boolean isEmpty(String str)
	{
	  return (str == null) || ("".equals(str.trim()));
	}
	
	/**
	 * 身份证号码加密处理，除了前面两位和后面两位，其他都替换成*
	 * @param idcard
	 * @return
	 */
	public static String idcardEncrypt (String idcard){
		
		String newStr = "";
		if(idcard.length()==15){
			newStr="***********";
		}else{
			newStr="**************";
		}

		idcard = idcard.replace(idcard.substring(2, idcard.length()-2),newStr);
		return idcard;
	}

	/**
	 * 社保卡号加密处理，除了前面两位和后面两位，其他都替换成*
	 * @param idcard
	 * @return
	 */
	public static String icnoEncrypt (String icno){
		
		String newStr = "*******";
		icno = icno.replace(icno.substring(1, icno.length()-1),newStr);
		return icno;
	}
	
	/**
	 * 手机号加密处理，除了前面三位和后面两位，其他都替换成*
	 * @param idcard
	 * @return
	 */
	public static String telEncrypt (String tel){
		
		String newStr = "********";
		tel = tel.replace(tel.substring(3, tel.length()-2),newStr);
		return tel;
	}
		
	/**
     * 将"Clob"型数据转换成"String"型数据
     * 需要捕获"SQLException","IOException"
     * prama:    colb1 将被转换的"Clob"型数据
     * return:    返回转好的字符串
     * @throws SQLException 
     * @throws IOException 
     */
    public static String clobToString(Clob colb) throws SQLException, IOException
    {
        String outfile = "";
        if(colb != null){
            //oracle.sql.CLOB clob = (oracle.sql.CLOB)colb1;        
            java.io.Reader is = colb.getCharacterStream();
            java.io.BufferedReader br = new java.io.BufferedReader(is);          
            String s = br.readLine();
            while (s != null) {
                outfile += s;
                s = br.readLine();
            }
            is.close();
            br.close();    
        }
        return  outfile;        
    }
}
