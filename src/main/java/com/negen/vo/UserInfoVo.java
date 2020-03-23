package com.negen.vo;

import lombok.Data;

import javax.persistence.Lob;
import java.util.List;

/**
 * @ Author     ：Negen
 * @ Date       ：Created in 8:56 2020/3/7
 * @ Description：返回登录用户的详细信息
 * @ Modified By：
 * @Version: 1.0
 * {"code":20000,"data":{"roles":["admin"],"introduction":"I am a super administrator","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif","name":"Super Admin"}}
 */
@Data
public class UserInfoVo {
    Long id;
    String name;
    String avatar;
    @Lob
    String introduction;
    List roles;
}
