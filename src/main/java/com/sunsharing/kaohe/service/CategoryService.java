/*
 * @(#) CategoryService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 21:19:53
 */

package com.sunsharing.kaohe.service;

import com.sunsharing.kaohe.pojo.Category;
import com.sunsharing.kaohe.utils.CallResult;

import java.util.List;

public interface CategoryService {

    CallResult saveOrUpdate(Category category);

    Category getCategoryName(String cname);

    void delete(Long id);

    List<Category> getCategoryList();
}
