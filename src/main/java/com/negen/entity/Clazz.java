package com.negen.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 15:34 2020/3/6
 * @ Description：班级实体
 * @ Modified By：
 * @Version: 1.0
 * 年级、班级、班主任、总人数
 */
@Data
@Table(name = "tb_clazz")
@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String grade;
    String clazz;
    String headTeacher;
    Integer totalStudent;           //限定总人数
    Integer currentTotalStudent;    //当前总人数
}
