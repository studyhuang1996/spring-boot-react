/*
 * @(#) DateTest
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-13 10:35:50
 */

package com.sunsharing.kaohe;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(String str, String format) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(format));
    }
    @Test
    public void test1(){
        System.out.println(toLocalDateTime(new Date()));
        //System.out.println(toLocalDateTime("2018-06-13T10:42:21.897","yyyy-MM-dd"));
        System.out.println(LocalDateTime.now());


    }
}
