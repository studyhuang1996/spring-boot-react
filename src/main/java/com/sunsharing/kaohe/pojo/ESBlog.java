/*
 * @(#) ESBlog
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-15 00:32:52
 */

package com.sunsharing.kaohe.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 创建博客实体，用于es学习
 * 文档
 */
@Data
@NoArgsConstructor
@Document(indexName="blog",type="blog")
public class ESBlog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String summary;

    private String content;

    public ESBlog(String title,String summary,String content){
        this.title = title;
        this.summary =summary;
        this.content =content;
    }

}
