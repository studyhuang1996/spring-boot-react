/*
 * @(#) MenuServiceImpl
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 23:13:59
 */

package com.sunsharing.kaohe.service.impl;

import com.sunsharing.kaohe.dao.mybatis.MenuMapper;
import com.sunsharing.kaohe.pojo.Menu;
import com.sunsharing.kaohe.service.MenuService;
import com.sunsharing.kaohe.utils.CallResult;
import com.sunsharing.kaohe.utils.ResultUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public CallResult saveOrUpdate(Menu menu) {
        if (menu.getMid() == null){
            menu.setMdate(new Date());
            menuMapper.insert(menu);
            return ResultUtils.success("保存成功");
        }
        menuMapper.update(menu);
        return ResultUtils.success("修改成功");
    }

    @Override
    public void deleteById(Long uid) {
          menuMapper.delete(uid);
    }



    @Override
    public List<Menu> getMenus() {
        return menuMapper.getMenus();
    }

    @Override
    public List<Menu> getMenusByName(String mname) {
        return menuMapper.getMenusByName(mname);
    }

    @Override
    public List<Menu> getMenusByCid(Long cid) {
        return menuMapper.getMenusByCid(cid);
    }
}
