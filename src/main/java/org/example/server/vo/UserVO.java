package org.example.server.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.enums.RoleEnum;
import org.example.server.po.User;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer id;

    private String username;

    private String imgURL;

    private RoleEnum role;

    private String password;

    private Date createTime;

    private List<Integer> followList;

    private List<Integer> fansList;

    private List<Integer> likeNewsList;

    private List<Integer> collectNewsList;

    private List<Integer> createNewsList;

    private List<Integer> createCommentList;

    private List<Integer> likeCommentList;

    public User toPO() {
        User user = new User();
        user.setId(this.id);
        user.setImgURL(this.imgURL);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setCreateTime(this.createTime);
        user.setRole(this.role);
        return user;
    }
}
