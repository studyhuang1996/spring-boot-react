/*
 * @(#) PropertiesUtil
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-14 15:21:51
 */

package com.sunsharing.kaohe.utils;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 *读取properties文件的工具类
 * 读取ftp的配置，防止硬编码
 */
@Slf4j
public class PropertiesUtil {
    private static Properties props;

    static {
        String fileName = "ftp.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader()
                .getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常",e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isEmpty(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isEmpty(value)){
            value = defaultValue;
        }
        return value.trim();
    }
    public static Integer getIntProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isEmpty(value)){
            return null;
        }
        return Integer.valueOf(value.trim());
    }

    public static Integer getIntProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isEmpty(value)){
            value = defaultValue;
        }
        return Integer.valueOf(value.trim());
    }


    // public static void main(String[] args) {
    //     log.info(PropertiesUtil.getProperty("ftp.server.ip"));
    //     log.info(PropertiesUtil.getProperty("ftp.user"));
    //     log.info(PropertiesUtil.getProperty("ftp.pass"));
    //     log.info(PropertiesUtil.getProperty("ftp.server.http.prefix"));
    //     log.info(PropertiesUtil.getIntProperty("ftp.server.port")+" ");
    //
    // }


}
