/*
 * @(#) FileFTPUtil
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-14 14:43:30
 */

package com.sunsharing.kaohe.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.ibatis.javassist.Loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by huang on 2018/3/18.
 */
@Slf4j
public class FileFTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileFTPUtil.class);
    private  static String ftpIp =PropertiesUtil.getProperty("ftp.server.ip");
    private  static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private  static String ftpPass = PropertiesUtil.getProperty("ftp.pass");
    private  static Integer ftpPort = PropertiesUtil.getIntProperty("ftp.server.port");

    private String ip;
    private Integer port;
    private String user;
    private String pass;
    private FTPClient ftpClient;

    public FileFTPUtil(String ip, Integer port, String user, String pass) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FileFTPUtil ftpUtils = new FileFTPUtil(ftpIp,ftpPort,ftpUser,ftpPass);
        logger.info("开始上传文件");
        Boolean result =ftpUtils.uploadFile("img",fileList);
        logger.info("上传文件成功");
        return result;
    }
    private   boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        Boolean upload = true;
        FileInputStream fis = null;
        //连接服务器
        if (connectServer(ip,port,user,pass)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for ( File fileItem: fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(),fis);
                }
            } catch (IOException e) {
                logger.error("连接ftp服务器异常",e);
                upload  = false;
                e.printStackTrace();
            }finally {
                fis.close();
                ftpClient.disconnect();
            }

        }
        return  upload;
    }

    private   boolean connectServer(String ip,int port,String user,String pass  ){
        boolean isSuccess =false;
        ftpClient =new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess =  ftpClient.login(user,pass);
        } catch (IOException e) {
            logger.error("连接ftp服务器异常",e);
            e.printStackTrace();
        }
        return  isSuccess;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
