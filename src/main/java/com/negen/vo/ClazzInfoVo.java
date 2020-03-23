package com.negen.vo;

import lombok.Data;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 11:06 2020/3/7
 * @ Description：班级详细信息实体
 * @ Modified By：
 * @Version: 1.0
 */
@Data
public class ClazzInfoVo {
    Long id;
    String grade;
    String clazz;
    String headTeacher;
    Integer totalStudent;           //限定总人数
    Integer currentTotalStudent;    //当前总人数
}
