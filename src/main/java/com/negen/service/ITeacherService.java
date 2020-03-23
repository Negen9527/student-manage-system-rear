package com.negen.service;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ServerResponse;
import com.negen.entity.Teacher;

public interface ITeacherService {

    //添加教师信息
    ServerResponse addTeacher(Teacher teacher);

    //修改教师信息
    ServerResponse updateTeacher(Teacher teacher);

    //查看教师
    ServerResponse listTeacher();

    //删除教师
    ServerResponse deleteTeacher(Long id);

    //查询教师
    ServerResponse searchTeacher(JSONObject obj);
}
