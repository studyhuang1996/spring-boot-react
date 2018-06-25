/*
 * @(#) ResultUtils
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 22:28:05
 */

/*
 * @(#) ResultUtils
 * 返回结果提取通用的方法
 * <br> @author huang
 * <br> 2018-04-23 14:12:31
 */

package com.sunsharing.kaohe.utils;

public class ResultUtils {

    public static CallResult success(Object object){
        CallResult result = new CallResult();
        result.setData(object);
        result.setCode(10000);
        result.setMsg("操作成功");
        return result;
    }

    /**
     *  {
     *   code: 10001,
     *   name: '校验错误',
     *   message: 'please input password',
     * }
     * @param msg
     * @return
     */
    public static CallResult error(String msg){
        CallResult result = new CallResult();

        result.setCode(10001);
      //  result.putData("name","校验错误");
        result.setMsg(msg);
        return result;
    }

}
