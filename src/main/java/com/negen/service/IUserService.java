package com.negen.service;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ServerResponse;
import com.negen.entity.User;

public interface IUserService {
    //用户注册
    ServerResponse userRegiste(User user);
    //用户登录
//    ServerResponse userLogin(User user);
    //获取用户信息
    ServerResponse getUserInfoByToken(String token);
    //修改用户信息
    ServerResponse updateUserInfo(User user);
    //修改用户密码
    ServerResponse updateUserPassword(JSONObject object);
}
