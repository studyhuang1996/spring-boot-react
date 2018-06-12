/*
 * @(#) MenuService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 23:13:45
 */

package com.sunsharing.kaohe.service;

import com.sunsharing.kaohe.pojo.Menu;
import com.sunsharing.kaohe.utils.CallResult;

import java.util.List;

public interface MenuService {


    CallResult saveOrUpdate(Menu user);

    void  deleteById(Long uid);


    List<Menu> getMenus(Integer page, Integer limit);

    List<Menu> getMenusByName(String mname);

    List<Menu> getMenusByCid(Long cid);
}
