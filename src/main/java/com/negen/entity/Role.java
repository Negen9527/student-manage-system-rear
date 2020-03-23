package com.negen.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 17:49 2020/3/5
 * @ Description：角色实体
 * @ Modified By：
 * @Version: 1.0
 */
@Entity
@Table(name = "tb_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String roleName;	//角色名称
    @OneToMany(cascade = {CascadeType.ALL} , fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    List<Permission> permissions;
}