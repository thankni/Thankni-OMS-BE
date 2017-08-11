package com.ldsmsoft.framework.webservice.server;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

public interface RestSample {  
  
    @GET  //@GET、@PUT、@POST、@DELETE：标注方法的HTTP请求类型。
    @Produces(MediaType.TEXT_PLAIN)  //标注返回的MIME媒体类型。
    public String doGet();  

    @GET  
    @Produces(MediaType.TEXT_PLAIN)  
    @Path("/request/{param}")  
    public String doRequest(@PathParam("param") String param,@Context HttpServletRequest servletRequest,  
            @Context HttpServletResponse servletResponse);  
   
   /**
    * 注册
    * @param name 姓名
    * @param idcard 身份证号码
    * @param icno 社保卡号
    * @param tel 手机号
    * @param password 密码
    * @return
    */
   @GET  
   @Path("/DE1001/{userName}/{tel}/{loginName}/{password}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
   HashMap<String, Object> regUser(@PathParam("userName")String name, @PathParam("tel")String tel, @PathParam("loginName")String loginName,@PathParam("password")String password);

   /**
    * 修改注册信息
    * @param userId
    * @param userName
    * @param idcard
    * @param tel
    * @param email
    * @param password
    * @param token
    * @param status
    * @return
    */
   @GET  
   @Path("/DE1002/{clientId}/{clientSecret}/{userId}/{userName}/{idcard}/{tel}/{email}/{password}/{token}/{status}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })   
   HashMap<String, Object> eidtRegInfo(@PathParam("clientId")String clientId, @PathParam("clientSecret")String clientSecret,String userId, String userName, String idcard, String tel, String email,
		   String password, String token, String status);
   /**
    * 登录
    * @param clientId 授权账号
    * @param clientSecret 授权码
    * @param loginName 登录名
    * @param password 密码
    * @return
    */
   @GET  
   @Path("/DE1003/{clientId}/{clientSecret}/{loginName}/{password}/{tk}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
   HashMap<String, Object> getPersonInfo(@PathParam("clientId")String clientId, @PathParam("clientSecret")String clientSecret, @PathParam("loginName")String loginName, @PathParam("password")String password, @PathParam("tk")String token);

   /**
    * 获取用户权限
    * @param token
    * @param clientId
    * @param clientSecret
    * @param userId
    * @return
    */
   @GET  
   @Path("/DE2001/{token}/{clientId}/{clientSecret}/{userId}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })   
   HashMap<String, Object> getPersonPower(@PathParam("token")String token, @PathParam("clientId")String clientId, @PathParam("clientSecret")String clientSecret, @PathParam("userId")String userId);

   
}  