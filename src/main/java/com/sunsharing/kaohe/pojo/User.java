/*
 * @(#) Admin
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 18:04:38
 */

package com.sunsharing.kaohe.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 用户表
 */
@Entity
@Data
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    private String uname;

    private String upassword;

    private String uemail;

    private Date ubirthday;

    private String utel;

    private String ustate;

    private Date  createTime;

    private Date updateTime;

}
