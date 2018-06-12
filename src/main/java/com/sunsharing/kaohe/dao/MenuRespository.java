/*
 * @(#) MenuRespository
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-01 09:49:55
 */

package com.sunsharing.kaohe.dao;

import com.sunsharing.kaohe.pojo.Menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRespository extends JpaRepository<Menu,Long> {
}
