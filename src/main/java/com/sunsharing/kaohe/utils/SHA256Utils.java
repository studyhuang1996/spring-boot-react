/*
 * @(#) SHA256Utils
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-12 14:17:09
 */

package com.sunsharing.kaohe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * SHA256 安全哈希算法的加密
 * 加盐:salt盐值在加密后在加上一段指定的字符串
 *
 */
public class SHA256Utils {


    public static  String SHA256Encode(String password){
        String resultString = null;
        // Random r = new Random();
        // StringBuilder sb = new StringBuilder(16);
        // sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        // int len = sb.length();
        // if (len < 16) {
        //     for (int i = 0; i < 16 - len; i++) {
        //         sb.append("0");
        //     }
        // }
        // String salt = sb.toString();
        resultString = new String(password);
         String str = null;
        try {
            //MessageDigest 类为应用程序提供信息摘要算法的功能
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(resultString.getBytes());
            //digest() 方法之一完成哈希计算
             byte[] bytes = digest.digest();
                str = bytes2Hex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    // public static void main(String[] args) {
    //     String password = SHA256Encode("admin");
    //     System.out.println( password);
    // }
}

