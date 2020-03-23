package com.negen.repository;

import com.negen.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {
    //统计班级学生人数
    List<Student> findByGradeAndClazz(String grade, String clazz);

    //根据学号模糊查询学生信息
    List<Student> findByNumContaining(String num);
    //根据姓名模糊查询学生信息
    List<Student> findByNameContaining(String name);

}
