package com.negen.service;

import com.negen.common.ServerResponse;
import com.negen.entity.Clazz;
import com.negen.vo.ClazzInfoVo;

public interface IClazzService {
    //添加班级
    ServerResponse addClazz(Clazz clazz);

    //列出所有班级
    ServerResponse listAllClazz();

    //修改班级信息
    ServerResponse updateClazz(Clazz clazz);

    //删除班级信息
    ServerResponse deleteClazz(Long id);

    //获取所有年级列表
    ServerResponse getAllGrade();

    //获取所有班级列表
    ServerResponse getAllClazz(String grade);

}
