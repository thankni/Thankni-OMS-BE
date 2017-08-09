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
     * 获取用户信息(VIEW_ACO1)
     * @param clientId 
	 * @param clientSecret 
     * @param sfzhm
     * @param icno
     * @return
     */
    @GET  
    @Path("/DE1001/{clientId}/{clientSecret}/{icno}/{sfzhm}")  
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })  
    public HashMap<String,Object> getPerson(@PathParam("clientId") String clientId,@PathParam("clientSecret") String clientSecret,@PathParam("icno") String icno,@PathParam("sfzhm") String sfzhm);  

   /**
	* 绑定用户信息(微信等)
    * @param clientId 授权账号
    * @param clientSecret 授权码
    * @param clientUserid 客户端账号（微信账号等）
    * @param sfzhm 身份证号码
    * @param icno 社保卡号
    * @param name 姓名
	* @return
	*/
   @GET  
   @Path("/DE5001/{clientId}/{clientSecret}/{clientUserid}/{sfzhm}/{icno}/{name}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) 
   HashMap<String, Object> bindUser(@PathParam("clientId") String clientId,@PathParam("clientSecret") String clientSecret,@PathParam("clientUserid") String clientUserid,@PathParam("sfzhm") String sfzhm,@PathParam("icno") String icno,@PathParam("name") String name);

   /**
	* 解绑用户信息(微信等)
	* @param clientId 授权账号
    * @param clientSecret 授权码
    * @param bindId 绑定信息id
	* @return
	*/
   @GET  
   @Path("/DE5002/{clientId}/{clientSecret}/{bindId}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) 
   HashMap<String, Object> unbindUser(@PathParam("clientId") String clientId,@PathParam("clientSecret") String clientSecret,@PathParam("bindId") String bindId);

   /**
    * 用户绑定信息查询
    * @param clientId 授权账号
    * @param clientSecret 授权码
    * @param clientUserid 客户端账号（微信账号等）
    * @return
    */
   @GET  
   @Path("/DE5003/{clientId}/{clientSecret}/{clientUserid}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON }) 
   HashMap<String, Object> getBindInfo(@PathParam("clientId") String clientId,@PathParam("clientSecret") String clientSecret,@PathParam("clientUserid") String clientUserid);

   
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
   HashMap<String, Object> regUser(@PathParam("name")String name, @PathParam("idcard")String idcard,@PathParam("icno")String icno,@PathParam("tel")String tel, @PathParam("password")String password);

   /**
    * 登录
    * @param clientId 授权账号
    * @param clientSecret 授权码
    * @param idcard 身份证号（账号）
    * @param password 密码
    * @return
    */
   @GET  
   @Path("/DE8002/{clientId}/{clientSecret}/{idcard}/{password}")  
   @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
   HashMap<String, Object> getPersonInfo(@PathParam("clientId")String clientId, @PathParam("clientSecret")String clientSecret, @PathParam("idcard")String idcard, @PathParam("password")String password);
   
}  