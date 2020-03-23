package com.negen.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
/**
 * @ Author     ：Negen
 * @ Date       ：Created in 17:49 2020/3/5
 * @ Description：用户实体
 * @ Modified By：
 * @Version: 1.0
 */
@Entity
@Table(name = "tb_user")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    /**
     * 账号
     */
    String userName;
    /**
     * 密码
     */
    String password;
    /**
     * 盐
     */
    String salt;
    /**
     * 头像
     */
    String avatar;
    /**
     * 简介
     */
    @Lob
    String introduction;
    String token;
    @OneToMany(cascade = {CascadeType.ALL} , fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    List<Role> roles;
}