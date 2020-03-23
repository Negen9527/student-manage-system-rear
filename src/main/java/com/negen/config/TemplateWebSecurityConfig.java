package com.negen.config;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 17:54 2020/3/5
 * @ Description：自定义security配置类
 * @ Modified By：
 * @Version: 1.0
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.User;
import com.negen.util.PrintUtil;
import com.negen.util.TokenUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.negen.repository.UserRepository;
import com.negen.service.impl.TemplateUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TemplateWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TemplateUserDetailsService templateUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                //过滤 swagger2
                .antMatchers("/user/create",
                        "/swagger*//**",
                        "/v2/api-docs",
                        "/webjars*//**").permitAll()
                //配置所有除上面以为的所有请求必须认证（登录）后才能访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                //登录接口地址
                .loginProcessingUrl("/login")
                //登录成功处理
                .successHandler(authenticationSuccessHandler())
                //登录失败处理
                .failureHandler(authenticationFailureHandler())
                .and().logout()
                .logoutSuccessUrl("/user/login")
//                .logoutSuccessHandler(onLogoutSuccess)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    // 密码加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置是否隐藏 UserNotFoundException
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(templateUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * 认证成功处理
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        // 认证（登录）成功
        return new AuthenticationSuccessHandler() {
            @SneakyThrows
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                //更新并返回token
                User user = userRepository.findByUserName(authentication.getName());

                String token = TokenUtil.createToken(Long.toString(user.getId()), user.getUserName());
                user.setToken(token);
                userRepository.save(user);
                HashMap dataMap = new HashMap();
                dataMap.put("token", token);
                System.out.println("Negen=======>登录成功");
                PrintUtil.response(response,
                        ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS).data(dataMap).toString());
            }
        };
    }

    // 认证失败处理
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                if (exception instanceof UsernameNotFoundException) {
                    // 账号不存在
                    PrintUtil.response(response,
                            ServerResponse.getInstance().responseEnum(ResponseEnum.ACCOUNT_NOT_FOUND).toString());
                    return;
                }
                // 密码错误
                PrintUtil.response(response,
                        ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_FAILED).toString());
            }
        };
    }

}