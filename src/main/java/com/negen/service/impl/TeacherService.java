package com.negen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.Teacher;
import com.negen.repository.TeacherRepository;
import com.negen.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 13:52 2020/3/12
 * @ Description：教师业务类
 * @ Modified By：
 * @Version: 1.0
 */
@Service
@Slf4j
public class TeacherService implements ITeacherService {
    private static final String NUM = "1";
    private static final String NAME = "2";
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public ServerResponse addTeacher(Teacher teacher) {
        try {
            teacherRepository.save(teacher);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse updateTeacher(Teacher teacher) {
        try {
            long id = teacher.getId();
            Teacher _teacher = teacherRepository.getOne(id);
            _teacher.setNum(teacher.getNum());
            _teacher.setAge(teacher.getAge());
            _teacher.setSex(teacher.getSex());
            _teacher.setName(teacher.getName());
            _teacher.setCourse(teacher.getCourse());
            teacherRepository.save(_teacher);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse listTeacher() {
        try {
            List<Teacher> teachers = teacherRepository.findAll();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(teachers);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse deleteTeacher(Long id) {
        try {
            teacherRepository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse searchTeacher(JSONObject obj) {
        try {
            String select = obj.getString("select");
            String content = obj.getString("content");
            List<Teacher> students = new ArrayList<>();
            switch (select){
                case NUM:
                    students = teacherRepository.findByNumContaining(content);
                    break;
                case NAME:
                    students = teacherRepository.findByNameContaining(content);
                    break;
                default:
                    break;
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(students);

        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
