/*
 * @(#) WebConfig
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-03 23:00:20
 */

/*
 * @(#) WebConfig
 * 配置拦截路径
 * <br> @author huang
 * <br> 2018-04-24 10:45:53
 */

package com.sunsharing.kaohe.web.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于使用xml进行配置
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    //添加登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
           多个拦截器组成一个拦截器链
           addPathPatterns 用于添加拦截规则
           excludePathPatterns 用户排除拦截
         */
        registry.addInterceptor(new LoginInterceptor())
            .addPathPatterns("users/**")
            .excludePathPatterns("/users/login");

    }

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
            .allowedHeaders("Access-Control-Allow-Origin")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
            .allowCredentials(true)
            .maxAge(3600);
    }
}
