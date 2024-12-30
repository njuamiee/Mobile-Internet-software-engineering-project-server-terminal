package org.example.devops_server.po;


//import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.devops_server.enums.RoleEnum;
import org.example.devops_server.vo.UserVO;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Basic
    @Column(name = "imgURL")
    private String imgURL;


    @Basic
    @Column(name = "password")
    private String password;

    //必须注意，在Java中用驼峰，在MySQL字段中用连字符_
    @Basic
    @Column(name = "create_time")
    private Date createTime;













    public UserVO toVO(){
        UserVO userVO=new UserVO();
        userVO.setId(this.id);
        userVO.setImgURL(this.imgURL);

        userVO.setUsername(this.username);
        userVO.setEmail(this.email);
        userVO.setPassword(this.password);
        userVO.setCreateTime(this.createTime);

        userVO.setRole(this.role);


        return userVO;
    }
}

