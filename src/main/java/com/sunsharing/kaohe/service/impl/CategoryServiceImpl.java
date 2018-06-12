/*
 * @(#) CategoryServiceImpl
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 21:21:30
 */

package com.sunsharing.kaohe.service.impl;

import com.sunsharing.kaohe.dao.CategoryRepository;
import com.sunsharing.kaohe.pojo.Category;
import com.sunsharing.kaohe.service.CategoryService;
import com.sunsharing.kaohe.utils.CallResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CallResult saveOrUpdate(Category category) {
       CallResult result =new CallResult();
       if (category.getCid()==null){
          Category categorys = categoryRepository.findByCname(category.getCname());
          if ( categorys !=null){
              result.setCode(10001);
              result.fail("该分类已经存在");
              return result;
          }
            categoryRepository.save(category);
          return result;
       }
       //修改
       categoryRepository.save(category);
       return result;

    }


    @Override
    public Category getCategoryName(String cname) {
        return categoryRepository.findByCname(cname);
    }

    @Override
    public void delete(Long id) {
      categoryRepository.delete(id);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }
}
