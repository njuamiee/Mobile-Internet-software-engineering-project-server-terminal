package org.example.server.po;


//import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.enums.RoleEnum;
import org.example.server.vo.UserVO;

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

    @ElementCollection
    @CollectionTable(name = "user_follow", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "follow_id")
    private List<Integer> followList;

    @ElementCollection
    @CollectionTable(name = "user_fans", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "fans_id")
    private List<Integer> fansList;

    //用户点赞的新闻
    @ElementCollection
    @CollectionTable(name = "user_like_news", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "news_id")
    private List<Integer> likeNewsList;

    //用户收藏的新闻
    @ElementCollection
    @CollectionTable(name = "user_collect_news", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "news_id")
    private List<Integer> collectNewsList;

    //用户创建的新闻
    @ElementCollection
    @CollectionTable(name = "user_create_news", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "news_id")
    private List<Integer> createNewsList;

    //用户创建的评论
    @ElementCollection
    @CollectionTable(name = "user_create_comment", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "comment_id")
    private List<Integer> createCommentList;

    //用户点赞的评论
    @ElementCollection
    @CollectionTable(name = "user_like_comment", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "comment_id")
    private List<Integer> likeCommentList;

    public UserVO toVO(){
        UserVO userVO=new UserVO();
        userVO.setId(this.id);
        userVO.setImgURL(this.imgURL);
        userVO.setUsername(this.username);
        userVO.setPassword(this.password);
        userVO.setCreateTime(this.createTime);
        userVO.setRole(this.role);
        return userVO;
    }
}

