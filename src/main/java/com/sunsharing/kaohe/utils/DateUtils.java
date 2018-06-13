/*
 * @(#) DateUtils
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-12 17:40:23
 */

package com.sunsharing.kaohe.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static final String DATETIME_FORMAT_STR = "yyyy-MM-dd";

    public static LocalDateTime toLocalDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(DATETIME_FORMAT_STR));
    }

    public static String toString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT_STR);
        return formatter.format(date);
    }
}
