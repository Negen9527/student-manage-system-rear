package com.negen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.negen.common.ResponseEnum;
import com.negen.common.ServerResponse;
import com.negen.entity.Student;
import com.negen.repository.StudentRespository;
import com.negen.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 14:07 2020/3/9
 * @ Description：学生信息业务类
 * @ Modified By：
 * @Version: 1.0
 */
@Slf4j
@Service
public class StudentService implements IStudentService {
    private static final String NUM = "1";
    private static final String NAME = "2";

    @Autowired
    StudentRespository studentRespository;

    @Override
    public ServerResponse addStudent(Student student) {
        try {
            if(null == student.getName() ||
               null == student.getAge() ||
               null == student.getSex()  ||
               null == student.getNum() ||
               null == student.getAddress() ||
               null == student.getGrade() ||
               null == student.getClazz()){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            }
            studentRespository.save(student);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }

    }

    @Override
    public ServerResponse listStudent() {
        try {
            List<Student> students = studentRespository.findAll();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(students);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse updateStudent(Student student) {
        try {
            if(     null == student.getId() ||
                    null == student.getName() ||
                    null == student.getAge() ||
                    null == student.getSex()  ||
                    null == student.getNum() ||
                    null == student.getAddress() ||
                    null == student.getGrade() ||
                    null == student.getClazz()){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            }
            studentRespository.save(student);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse deleteStudent(Long id) {
        try {
            studentRespository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse searchStudent(JSONObject object) {
        try {
            String select = object.getString("select");
            String content = object.getString("content");
            List<Student> students = new ArrayList<>();
            switch (select){
                case NUM:
                    students = studentRespository.findByNumContaining(content);
                    break;
                case NAME:
                    students = studentRespository.findByNameContaining(content);
                    break;
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(students);
        }catch (Exception e){
            log.info(e.getMessage());
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
