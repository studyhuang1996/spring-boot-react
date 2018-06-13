/*
 * @(#) MenuController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 22:43:22
 */

package com.sunsharing.kaohe.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsharing.kaohe.pojo.Menu;
import com.sunsharing.kaohe.service.MenuService;
import com.sunsharing.kaohe.utils.CallResult;
import com.sunsharing.kaohe.utils.ResultUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RequestMapping("menu")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("list")
    public CallResult listMenus(){
        PageHelper.startPage(2,5);
       List<Menu> menus =menuService.getMenus();
       if (CollectionUtils.isEmpty(menus)){
           return ResultUtils.error("菜谱列表为空");
       }
        return ResultUtils.success(menus);
    }

    @PostMapping("save")
    public CallResult save(@Valid Menu menu, BindingResult bindingResult) {
        CallResult callResult = new CallResult();
        if (null == menu) {
            callResult.fail("请输入所要保存的信息");
            return callResult;
        }
        callResult = menuService.saveOrUpdate(menu);
        return callResult;
    }

    @GetMapping("delete/{mid}")
    public CallResult deleteMenus(@PathVariable Long mid) {
        CallResult callResult = new CallResult();

        menuService.deleteById(mid);

        return callResult;
    }

    @RequestMapping("list/{cid}")
    @ResponseBody
    public CallResult getMenusByCid(@PathVariable Long cid) {
        CallResult callResult = new CallResult();
        List<Menu> menus = menuService.getMenusByCid(cid);

        callResult.setData(menus);
        return callResult;
    }

    @RequestMapping("query")
    @ResponseBody
    public CallResult getMenusByName(String mname) {
        CallResult callResult = new CallResult();
        List<Menu> menus = menuService.getMenusByName(mname);
        callResult.setData(menus);
        return callResult;
    }


}
