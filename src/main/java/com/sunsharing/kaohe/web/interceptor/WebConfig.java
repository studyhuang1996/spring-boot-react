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
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    //添加session拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            .addPathPatterns("user/**")
            .excludePathPatterns("/user/login");
        //super.addInterceptors(registry);
    }
}
