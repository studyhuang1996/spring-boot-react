/*
 * @(#) BlogRepository
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-15 00:39:39
 */

package com.sunsharing.kaohe.dao;

import com.sunsharing.kaohe.pojo.ESBlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<ESBlog,Integer> {

    /**
     * 分页查询博客 去重
     * @param title
     * @param summary
     * @param content
     * @param pageable
     * @return
     */
    Page<ESBlog> findDistinctByTitleContainingOrSummaryContainingOrContentContaining(
                   String title,String summary,String content,Pageable pageable);
}
