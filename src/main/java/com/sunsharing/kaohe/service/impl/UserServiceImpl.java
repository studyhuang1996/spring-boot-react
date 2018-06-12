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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveOrUpdate(User user) {
       userRepository.save(user);
    }



    @Override
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
