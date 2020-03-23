package com.negen.repository;

import com.negen.entity.Teacher;
import com.negen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 17:51 2020/3/5
 * @ Description：教师repository
 * @ Modified By：
 * @Version: 1.0
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    //根据学号模糊查询教师信息
    List<Teacher> findByNumContaining(String num);
    //根据姓名模糊查询教师信息
    List<Teacher> findByNameContaining(String name);
}