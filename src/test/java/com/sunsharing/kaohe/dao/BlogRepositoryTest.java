/*
 * @(#) BlogRepositoryTest
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-15 11:38:32
 */

package com.sunsharing.kaohe.dao;

import com.sunsharing.kaohe.pojo.ESBlog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogRepositoryTest {

    @Autowired
    BlogRepository blogRepository;

  //  @Before
    public void initBlog(){
      //  blogRepository.deleteAll();
       blogRepository.save(new ESBlog("标题","总结222","内容123"));
       blogRepository.save(new ESBlog("标题123","总结111","内容"));
       blogRepository.save(new ESBlog("标题12","总结","内容"));
       blogRepository.save(new ESBlog("标题14","总结","内容"));
    }

     @Test
     public void save(){
         ESBlog esBlog = new ESBlog("标题","总结222","内容123");
         blogRepository.save(esBlog);
     }

    @Test
    public void findDistinctByTitleContainingOrSummaryContainingOrContentContaining() {
       Pageable page = new PageRequest(0,20);
       String title="标";
       String summary="题";
       String content="内";
      Page<ESBlog> blogs = blogRepository.findDistinctByTitleContainingOrSummaryContainingOrContentContaining(
           title,summary,content,page);
     for (ESBlog esBlog : blogs){
         System.out.println(esBlog.toString());
     }
    }
}