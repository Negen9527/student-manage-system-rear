package com.negen.controller;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ServerResponse;
import com.negen.entity.Teacher;
import com.negen.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 12:13 2020/3/12
 * @ Description：教师控制层
 * @ Modified By：
 * @Version: 1.0
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    ITeacherService teacherService;
    //添加教师
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addTeacher(@RequestBody Teacher teacher){
        return teacherService.addTeacher(teacher);
    }

    //修改教师
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse updateTeacher(@RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }

    //查看教师
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ServerResponse listTeacher(){
        return teacherService.listTeacher();
    }
    //删除教师
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public ServerResponse deleteTeacher(@PathVariable long id){
        return teacherService.deleteTeacher(id);
    }
    //查询教师
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ServerResponse searchTeacher(@RequestBody JSONObject jsonObj){
        return teacherService.searchTeacher(jsonObj);
    }

}
