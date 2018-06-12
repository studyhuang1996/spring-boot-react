/*
 * @(#) LoginInterceptor
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-03 23:00:20
 */

/*
 * @(#) LoginInterceptor
 * 设置拦截器,拦截登录验证
 * <br> @author huang
 * <br> 2018-04-24 09:42:55
 */

package com.sunsharing.kaohe.web.interceptor;


import com.sunsharing.kaohe.utils.Const;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session拦截
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException{
        HttpSession session = request.getSession();
        response.setContentType("text/html; charset=utf-8");

        Object object =  session.getAttribute(Const.CURR_USER);
        if (object != null){
            return  true;
        }

        PrintWriter writer = response.getWriter();
        writer.print("用户未登录,请先登录");
        writer.flush();
      //  response.sendRedirect("/user/login");
        return false;
    }
}
