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
   @Path("/DE8001/{name}/{idcard}/{icno}/{tel}/{password}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
   HashMap<String, Object> regUser(@PathParam("name")String name, @PathParam("loginName")String loginName,@PathParam("icno")String icno,@PathParam("tel")String tel, @PathParam("password")String password);

   /**
    * 登录
    * @param clientId 授权账号
    * @param clientSecret 授权码
    * @param loginName 登录名
    * @param password 密码
    * @return
    */
   @GET  
   @Path("/DE8002/{clientId}/{clientSecret}/{loginName}/{password}/{tk}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
   HashMap<String, Object> getPersonInfo(@PathParam("clientId")String clientId, @PathParam("clientSecret")String clientSecret, @PathParam("loginName")String loginName, @PathParam("password")String password, @PathParam("tk")String token);

HashMap<String, Object> getPersonPower(String token, String clientId, String clientSecret, String userId);
   
}  