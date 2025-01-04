package org.example.devops_server.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.devops_server.enums.RoleEnum;
import org.example.devops_server.po.User;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer id;

    private String email;

    private String username;

    private String imgURL;

    private RoleEnum role;

    private String password;

    private Date createTime;


    public User toPO() {
        User user = new User();
        user.setId(this.id);

        user.setImgURL(this.imgURL);

        user.setUsername(this.username);

        user.setPassword(this.password);
        user.setCreateTime(this.createTime);
        user.setEmail(this.email);
        user.setRole(this.role);

        return user;
    }
}
