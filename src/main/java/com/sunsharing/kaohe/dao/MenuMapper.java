/*
 * @(#) MenuMapper
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 23:17:35
 */

package com.sunsharing.kaohe.dao;

import com.sunsharing.kaohe.pojo.Menu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MenuMapper {

    void insert(Menu menu);

    void update(Menu menu);

    void delete(Long id);

    List<Menu> getMenus();

    List<Menu> getMenusByName(@Param("mname") String mname);

    List<Menu> getMenusByCid(Long cid);

}
