package com.negen.service;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ServerResponse;
import com.negen.entity.Student;

public interface IStudentService {
    //新增学生信息
    ServerResponse addStudent(Student student);
    //学生列表
    ServerResponse listStudent();
    //修改学生信息
    ServerResponse updateStudent(Student student);
    //删除学生信息
    ServerResponse deleteStudent(Long id);
    //查询学生信息
    ServerResponse searchStudent(JSONObject object);
}
