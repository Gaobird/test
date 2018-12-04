/**
 * AccessFilter.JAVA
 * Copyright2018 天津亿网通达网络技术有限公司
 * All rights reserved
 * Created on 2018/11/2 11:14
 **/
package com.filter;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *文件的说明
 *@author : 陆柏瑾
 *@Version : 1.0.0 , 2018/11/2 11:14
 **/
public class AccessFilter implements Filter {
    private static final long serialVersionUID = 1L;

    //@Value("${}")
    //@Value("*")
    //private String projectDemoAllowOriginUrl;
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("这里是跨域请求过滤器");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        /** 允许所有的跨域请求 */
        httpResponse.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods","*");
        filterChain.doFilter(servletRequest, httpResponse);
       /* Subject subject = SecurityUtils.getSubject();
        subject.toString();
        if(subject.hasRole("admin")) {
            System.out.println("过滤器权限！！！！！！！");
        }*/

    }

    @Override
    public void destroy() {

    }
}
