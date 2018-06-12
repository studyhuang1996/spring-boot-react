/*
 * @(#) CallResult
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 21:25:27
 */

package com.sunsharing.kaohe.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 构造通用json返回值
 */
public class CallResult{

    private boolean succee = true;
    private Integer code;
    private String msg = "操作成功";
    private Object data;

    Map<String, Object> datas;

    public CallResult() {

    }

    public CallResult(boolean succee, String msg) {
        this.msg = msg;
        this.succee = succee;

    }
    public void fail(Integer code, String msg) {
        this.succee = false;
        this.msg = msg;
        this.code = code;
    }

    public void fail(String msg) {
        this.succee = false;
        this.msg = msg;
    }

    public void fail(Integer code) {
        this.succee = false;
        this.code = code;
    }

    public void succee(String msg) {
        this.succee = true;
        this.msg = msg;
    }

    public boolean isSuccee() {
        return succee;
    }

    public void setSuccee(boolean succee) {
        this.succee = succee;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public void putData(String key, Object value) {
        if (datas == null) {
            datas = new HashMap<String, Object>();
        }
        datas.put(key, value);
    }

    public Object getData(String key) {
        if (datas != null) {
            return datas.get(key);
        }
        return null;
    }

}
