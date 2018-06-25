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
import com.sunsharing.kaohe.utils.CallResult;
import com.sunsharing.kaohe.utils.DateUtils;
import com.sunsharing.kaohe.utils.ResultUtils;
import com.sunsharing.kaohe.utils.SHA256Utils;
import com.sunsharing.kaohe.utils.VerifyObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CallResult saveOrUpdate(User user) {
        //保存
        if (user.getId() == null) {
            user.setCreateTime(new Date());
            if (!StringUtils.isEmpty(user.getPassword())){
                //用注册时间当盐值
                String date =  DateUtils.toString(new Date());
                user.setPassword(SHA256Utils.SHA256Encode(user.getPassword()+date));
                log.info(user.getPassword());
            }
            User  currUser = userRepository.getUserByUsername(user.getUsername());
            if (VerifyObject.verify(currUser)) {
                return ResultUtils.error("该用户名已存在");
            }
        }else{
            //编辑
            Date createTime = userRepository.findOne(user.getId()).getCreateTime();
            if (!StringUtils.isEmpty(user.getPassword())){
                //用注册时间当盐值
                String date =  DateUtils.toString(createTime);
                user.setPassword(SHA256Utils.SHA256Encode(user.getPassword()+date));
            }
            user.setCreateTime(createTime);
            user.setUpdateTime(new Date());
        }
        userRepository.save(user);
        return ResultUtils.success(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
       userRepository.delete(id);
    }

    @Override
    public User get(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
