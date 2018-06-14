/*
 * @(#) UserController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-05-31 22:23:09
 */

package com.sunsharing.kaohe.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsharing.kaohe.pojo.User;
import com.sunsharing.kaohe.service.UserService;
import com.sunsharing.kaohe.utils.CallResult;
import com.sunsharing.kaohe.utils.Const;
import com.sunsharing.kaohe.utils.DateUtils;
import com.sunsharing.kaohe.utils.RedisUtils;
import com.sunsharing.kaohe.utils.ResultUtils;
import com.sunsharing.kaohe.utils.SHA256Utils;
import com.sunsharing.kaohe.utils.VerifyObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.security.provider.SHA;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @PostMapping("login")
    @ResponseBody
    public CallResult login(User user, HttpSession session){

        if (!VerifyObject.verify(user)){
            return ResultUtils.error("参数传递错误");         }
        User  currUser = userService.getUserByName(user.getUname());
        if (!VerifyObject.verify(currUser)) {
            return ResultUtils.error("该用户不存在");
        }
        //获取用户的密码并加密
        String salt = DateUtils.toString(currUser.getCreateTime());
        String userPwd =  SHA256Utils.SHA256Encode(user.getUpassword()+salt);
       // System.out.println(userPwd);
        if (!(userPwd.equals(currUser.getUpassword()))) {
            return ResultUtils.error("密码输入错误");
        }

        if (redisTemplate.hasKey("userId")){
            redisTemplate.delete("userId");
        }
        session.setAttribute(Const.CURR_USER,currUser);
        ValueOperations<String,Object> template =redisTemplate.opsForValue();
        //存sessionId
        template.set("userId",session.getId());
        //设置过期时间
        redisTemplate.expire("userId", 30,TimeUnit.MINUTES);
        return ResultUtils.success(user);
    }


    @GetMapping("get/{id}")
    @ResponseBody
    public CallResult getUserInfo(@PathVariable("id") Long id){
        if (null == id){
            return ResultUtils.error("参数为空");
        }
        User user = userService.get(id);
        if (!VerifyObject.verify(user)) {
            return ResultUtils.error("该用户不存在");
        }
        return ResultUtils.success(user);
    }

    /**
     * 注册用户
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public  CallResult saveUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtils.error(bindingResult.getFieldError().getDefaultMessage());
        }

        User  currUser = userService.getUserByName(user.getUname());
        if (VerifyObject.verify(currUser)) {
            return ResultUtils.error("该用户名已存在");
        }
         userService.saveOrUpdate(user);

        return ResultUtils.success(null);
    }

    @GetMapping("list")
    @ResponseBody
    public CallResult listUsers(){

        List<User> users = userService.getUserList();

        if (CollectionUtils.isEmpty(users)){
            return ResultUtils.error("没数据");
        }
        return  ResultUtils.success(users);
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public  CallResult deleteById(@PathVariable Long id){
        if (null == id){

            return ResultUtils.error("没数据");
        }
         if (userService.get(id)== null){
            return ResultUtils.error("没有该用户，不可删除");
         };
        userService.delete(id);
        return ResultUtils.success(null);

    }


}