/*
 * @(#) UploadController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-14 11:30:46
 */

package com.sunsharing.kaohe.web.controller;

import com.sunsharing.kaohe.utils.CallResult;
import com.sunsharing.kaohe.utils.FileUtils;
import com.sunsharing.kaohe.utils.ResultUtils;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("upload")
@RestController
@Slf4j
public class UploadController {

    @RequestMapping("img")
    public CallResult uploadImg(@RequestParam("file")MultipartFile multipartFile,
                                HttpServletRequest request){
          String path =request.getServletContext().getRealPath("upload");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //@RequestParam("pic")MultipartFile multipartFile
        // MultipartFile multipartFile = multipartRequest.getFile("file");
         String fileName =  FileUtils.uploadFile(multipartFile,path);
         log.info(fileName);
        if (StringUtils.isEmpty(fileName)){

            return ResultUtils.error("上传失败");
        }


        return ResultUtils.success(true);
    }
}
