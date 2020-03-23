package com.negen.service.impl;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.negen.entity.Permission;
import com.negen.entity.Role;
import com.negen.entity.User;
import com.negen.repository.UserRepository;
import org.springframework.stereotype.Service;
/**
 * @ Author     ：Negen
 * @ Date       ：Created in 17:53 2020/3/5
 * @ Description：自定义userdetail
 * @ Modified By：
 * @Version: 1.0
 */

@Slf4j
@Service
public class TemplateUserDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loginUser = userRepository.findByUserName(username);
        if (null == loginUser) {
            //账号不存在，抛出异常
            throw new UsernameNotFoundException(username);
        } else {
            //用户存在，创建 SimpleGrantedAuthority集合
            List<SimpleGrantedAuthority> authorities =
                    new ArrayList<SimpleGrantedAuthority>();
            //遍历角色
            for(Role role:loginUser.getRoles()) {
                //遍历权限
                for(Permission permission:role.getPermissions()) {
                    //根据权限名称创建 SimpleGrantedAuthority
                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority(permission.getPermissionName());
                    authorities.add(authority);
                }
            }
            return new org.springframework.security.core.userdetails.User(
                    username,	//用户名
                    loginUser.getPassword(),	//用户密码
                    authorities		//权限集合
            );
        }
    }
}