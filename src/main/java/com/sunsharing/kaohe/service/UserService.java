/*
 * @(#) AdminService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 18:15:08
 */

package com.sunsharing.kaohe.service;


import com.sunsharing.kaohe.pojo.User;
import com.sunsharing.kaohe.utils.CallResult;

import java.util.List;

public interface UserService {

    CallResult saveOrUpdate(User user);

    void delete(Integer id);

     User get(Integer id);

     User getUserByName(String username);

     List<User> getUserList();
}
