/*
 * @(#) FileUtils
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-14 11:34:45
 */

package com.sunsharing.kaohe.utils;

import com.google.common.collect.Lists;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

    public static String uploadFile(MultipartFile file,String path){
        //文件名
         String fileName = file.getOriginalFilename();
         //后缀
         String lastName = fileName.substring(fileName.lastIndexOf(".")+1);
        //重新命名，避免冲突
        String newFileName = UUID.randomUUID().toString()+"."+lastName;

        File fileDir = new File(path);

        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdir();
        }
        // parent 路径名字符串和 child 路径名字符串创建一个新 File 实例
        File fileTarget = new File(path,newFileName);

        try {
            //把内存中的图片写入磁盘
            file.transferTo(fileTarget);
            //文件上传成功
            //上传到ftp
            FileFTPUtil.uploadFile(Lists.newArrayList(fileTarget));
            //删除upload文件夹下的图片
            fileTarget.delete();
        } catch (IOException e) {
            log.error("文件上传失败");
            e.printStackTrace();
        }
        return fileTarget.getName();
    }
}
