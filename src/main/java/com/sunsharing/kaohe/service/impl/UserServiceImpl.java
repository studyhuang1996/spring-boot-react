/*
 * @(#) UserServiceImpl
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 21:01:06
 */

package com.sunsharing.kaohe.service.impl;

import com.sunsharing.kaohe.dao.UserRepository;
import com.sunsharing.kaohe.pojo.User;
import com.sunsharing.kaohe.service.UserService;
import com.sunsharing.kaohe.utils.DateUtils;
import com.sunsharing.kaohe.utils.SHA256Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveOrUpdate(User user) {

        if (!StringUtils.isEmpty(user.getUpassword())){
            //用注册时间当盐值
           String date =  DateUtils.toString(new Date());
            user.setUpassword(SHA256Utils.SHA256Encode(user.getUpassword()+date));
            System.out.println(user.getUpassword());
        }
       if (user.getUid() == null) {
            user.setCreateTime(new Date());
       }else{
            user.setUpdateTime(new Date());
       }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       userRepository.delete(id);
    }

    @Override
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getUserByUname(username);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
