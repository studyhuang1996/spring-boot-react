/*
 * @(#) AdminRepository
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 18:13:40
 */

package com.sunsharing.kaohe.dao;


import com.sunsharing.kaohe.pojo.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

   User getUserByUname(String uname);

   void deleteUserByUid(Long uid);

}
