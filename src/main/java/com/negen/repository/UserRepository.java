package com.negen.repository;

import com.negen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 17:51 2020/3/5
 * @ Description：用户repository
 * @ Modified By：
 * @Version: 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 通过token获取用户
     * @param token
     * @return
     */
    User findByToken(String token);

    /**
     * 通过id和密码获取用户
     * @param id
     * @param password
     * @return
     */
    User findByIdAndPassword(Long id, String password);
}