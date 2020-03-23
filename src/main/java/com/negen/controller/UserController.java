package com.negen.controller;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.User;
import com.negen.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：Negen
        * @ Date       ：Created in 22:59 2020/3/5
        * @ Description：用户控制类
        * @ Modified By：
        * @Version: 1.0
        */
@RestController
public class UserController {
    @Autowired
    IUserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "user/create", method = RequestMethod.POST)
    ServerResponse userRegister(@RequestBody User user){
        return userService.userRegiste(user);
    }


    @RequestMapping(value = "user/login")
    ServerResponse userLogin(){
        return ServerResponse.getInstance().responseEnum(ResponseEnum.ACCOUT_NOT_LOGIN);
    }

    @RequestMapping(value = "user/info", method = RequestMethod.GET)
    ServerResponse getUserInfo(HttpServletRequest request){
        String token = request.getParameter("token");
        return userService.getUserInfoByToken(token);
    }


    @RequestMapping(value = "user/info/update", method = RequestMethod.POST)
    ServerResponse updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @RequestMapping(value = "user/info/updatePassword", method = RequestMethod.POST)
    ServerResponse updatePassword(@RequestBody JSONObject jsonObject){
        return userService.updateUserPassword(jsonObject);
    }
}
