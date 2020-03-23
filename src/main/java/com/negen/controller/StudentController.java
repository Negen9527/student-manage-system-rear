package com.negen.controller;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ServerResponse;
import com.negen.entity.Student;
import com.negen.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 14:26 2020/3/9
 * @ Description：学生控制类
 * @ Modified By：
 * @Version: 1.0
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    IStudentService studentService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ServerResponse listStudent(){
        return studentService.listStudent();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }


    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public ServerResponse deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ServerResponse searchStudent(@RequestBody JSONObject reqJson){
        return studentService.searchStudent(reqJson);
    }
}
