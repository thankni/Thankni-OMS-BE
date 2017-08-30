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
     * 获取商品分类(多条)
     * @return
     */
    @GET  
    @Path("/QU3001")  
    @Produces({ MediaType.APPLICATION_JSON })   
    HashMap<String, Object> getClazzs();
    /**
     * 获取商品(多条)
     * @return
     */
    @GET  
    @Path("/QU3002/{clazzId}/{page}/{pageSize}")  
    @Produces({ MediaType.APPLICATION_JSON })   
    HashMap<String, Object> getProductions(String clazzId,String page,String pageSize);
    /**
     * 获取商品计划(多条)
     * @return
     */
    @GET  
    @Path("/QU3003/{productionId}/{page}/{pageSize}")  
    @Produces({ MediaType.APPLICATION_JSON })   
    HashMap<String, Object> getProductionPlans(String productionId,String page,String pageSize);
    /**
     * 新增商品
     * @return
     */
    @GET  
    @Path("/AD3001")  
    @Produces(MediaType.APPLICATION_JSON)   
    HashMap<String, Object> addProduction();
    /**
     * 修改商品
     * @return
     */
    @GET  
    @Path("/ED3001")  
    @Produces(MediaType.APPLICATION_JSON)   
    HashMap<String, Object> eidtProduction();
    /**
     * 新增商品计划
     * @return
     */
    @GET  
    @Path("/AD3002")  
    @Produces(MediaType.APPLICATION_JSON)   
    HashMap<String, Object> addProductionPlan();
    /**
     * 新增商品计划
     * @return
     */
    @GET  
    @Path("/ED3002")  
    @Produces(MediaType.APPLICATION_JSON)   
    HashMap<String, Object> editProductionPlan();
    
    
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

/*   *//**
    * 获取用户权限
    * @param token
    * @param clientId
    * @param clientSecret
    * @param userId
    * @return
    *//*
   @GET  
   @Path("/DE1004/{token}/{clientId}/{clientSecret}/{userId}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })   
   HashMap<String, Object> getPersonPower(@PathParam("token")String token, @PathParam("clientId")String clientId, @PathParam("clientSecret")String clientSecret, @PathParam("userId")String userId);
*/
}  