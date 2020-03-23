package com.negen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.User;
import com.negen.repository.UserRepository;
import com.negen.service.IUserService;
import com.negen.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;


/**
 * @ Author     ：Negen
 * @ Date       ：Created in 21:06 2020/3/5
 * @ Description：用户业务实现类
 * @ Modified By：
 * @Version: 1.0
 */
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public ServerResponse userRegiste(User user) {
        try {
            if(null == user.getUserName() || null==user.getPassword())
                return  ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            //用户已存在
            if(null != userRepository.findByUserName(user.getUserName()))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXSIT);

            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.REGISTE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse getUserInfoByToken(String token) {
        User user = userRepository.findByToken(token);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setId(user.getId());
        userInfoVo.setName(user.getUserName());
        userInfoVo.setAvatar(user.getAvatar());
        userInfoVo.setIntroduction(user.getIntroduction());
        userInfoVo.setRoles(new ArrayList());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(userInfoVo).code(20000);
    }

    @Override
    public ServerResponse updateUserInfo(User user) {
        try {
            Long id = user.getId();
            String introduction = user.getIntroduction();
            User cUser = userRepository.getOne(id);
            if(!StringUtils.isEmpty(introduction)){
                cUser.setIntroduction(introduction);
                userRepository.save(cUser);
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse updateUserPassword(JSONObject object) {
        try {
            Long id = object.getLong("id");
            User cUser = userRepository.getOne(id);

            //旧密码
            String oldP = object.getString("oldP");
            String oldPStr = new BCryptPasswordEncoder().encode(oldP);
            //新密码
            String newP = object.getString("newP");
            String newPStr = new BCryptPasswordEncoder().encode(newP);

            if(! new BCryptPasswordEncoder().matches(oldP, cUser.getPassword())){
                //密码错误
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_FAILED);
            }else{
                cUser.setPassword(newPStr);
                userRepository.save(cUser);
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS).message("密码修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
