package com.hnepsoft.framework.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  
  
@Controller
@RequestMapping(value="/file")
public class fileServerController { 
         
    /**
     * 文件下载  
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value="/download.do")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {  
          
        //获得请求文件名  
        String filename = request.getParameter("filename");  
        //设置文件MIME类型  
        response.setContentType(request.getServletContext().getMimeType(filename));  
        //设置Content-Disposition  
        response.setHeader("Content-Disposition", "attachment;filename="+filename);  
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
        String fullFileName = request.getServletContext().getRealPath("/download/" + filename);  
        //读取文件  
        InputStream in = new FileInputStream(fullFileName);  
        OutputStream out = response.getOutputStream();  
          
        //写文件  
        int b;  
        while((b=in.read())!= -1)  
        {  
            out.write(b);  
        }  
          
        in.close();  
        out.close();  
    }  
  
}
