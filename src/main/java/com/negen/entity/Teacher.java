package com.negen.entity;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 15:34 2020/3/6
 * @ Description：教师实体
 * @ Modified By：
 * @Version: 1.0
 * 	姓名、年龄、性别、工号、科目
 */
@Data
@Table(name = "tb_teacher")
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer age;
    Integer sex;
    String num;
    String course;
}
