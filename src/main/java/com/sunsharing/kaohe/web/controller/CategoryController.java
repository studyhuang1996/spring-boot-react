/*
 * @(#) CategoryController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 21:22:25
 */

package com.sunsharing.kaohe.web.controller;

import com.sunsharing.kaohe.pojo.Category;
import com.sunsharing.kaohe.service.CategoryService;
import com.sunsharing.kaohe.utils.CallResult;
import com.sunsharing.kaohe.utils.ResultUtils;
import com.sunsharing.kaohe.utils.VerifyObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RequestMapping("category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public CallResult listCategories(){
        CallResult result = new CallResult();
        List<Category> categories = categoryService.getCategoryList();
        if (CollectionUtils.isEmpty(categories)){
             result.fail("没有分类");
             return result;
        }

        return ResultUtils.success(categories);
    }

    /**
     * 增加修改分类
     * @param category
     * @param bindingResult
     * @return
     */
    @PostMapping("save")
    public CallResult saveCategories(@Valid Category category, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtils.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return categoryService.saveOrUpdate(category);
    }


    @GetMapping("delete/{cid}")
    public CallResult deleteCategories(@PathVariable Long cid){
        if (cid ==null){
            return ResultUtils.error("参数为空");
        }

        categoryService.delete(cid);
        return ResultUtils.success(null);
    }


}
